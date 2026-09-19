package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.RadioButtonList;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.DataSourceUtil;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.grideditor.ShengShiGridData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 表格与表单（弹出窗体，表格中保存，省市联动）：在上一页的基础上加了「省 / 市」两列，市列表跟着省联动。
 *
 * <p>省市数据只有一份：省份列表 {@link DataSourceUtil#SHENG} 在首屏绑到省下拉列表上；「省 → 市」的对应关系
 * {@link #getShiJson()} 输出到页面脚本里，客户端切换省份时按它重新加载市下拉列表。表格里的省市两列直接存名称，
 * 不需要在客户端把代码翻译成名称。
 *
 * <p>没选省时市下拉列表被禁用并提示「请先选择省！」；切换省份后原市若不属于新省份会被清空。演示数据里特意留了
 * 几行没有省市的记录，用来看这个效果。
 *
 * <p>新增行的位置由客户端在回发数据里带上来（{@code index}），所以服务端能把它插回原来的位置——前提是表格
 * 不分页、也没在客户端排过序，否则客户端看到的行序和数据源的行序对不上，插进去的位置就是错的。
 *
 * <p>数据存在会话里，模拟一张能改的数据表。真实项目不要在会话里放大量数据，否则会严重影响服务器性能。
 */
@FineUIPage("grid/form-window-cell-edit-sheng-shi")
public class FormWindowCellEditShengShi extends PageBase {

    private static final String SESSION_KEY = "Grid.FormWindowCellEditShengShi";
    private static final String[] COLUMNS = {"Name", "Gender", "EntranceYear", "EntranceDate", "AtSchool", "Major", "Sheng", "Shi"};

    Grid Grid1;
    Window Window1;
    Label labResult;
    HiddenField hfFormID;
    TextBox tbxFormUserName;
    RadioButtonList rblFormGender;
    NumberBox nbFormEntranceYear;
    DatePicker dpFormEntranceDate;
    CheckBox cbFormAtSchool;
    DropDownList ddlFormMajor;
    DropDownList ddlSheng;
    DropDownList ddlShi;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 省下拉列表：全部省份（名称即值）
            ddlSheng.setDataSource(DataSourceUtil.SHENG);
            ddlSheng.dataBind();

            bindGrid();
        }
    }

    /** 省→市数据（名称）的 JSON，供模板输出到 window._SHI 供客户端联动使用。 */
    public String getShiJson() {
        return Json.encode(DataSourceUtil.SHI);
    }

    /** 全部保存：把客户端攒下的这一批改动落到会话里的数据表。 */
    public void btnSaveAll_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> modifiedData = Grid1.getModifiedData();
        if (modifiedData.isEmpty()) {
            labResult.setText("");
            showNotify("表格数据没有变化！");
            return;
        }

        List<Map<String, Object>> source = sourceData();

        // 修改与删除先处理：新增行要等删除处理完再插，插进去的行序才与客户端一致
        for (Map<String, Object> modifiedRow : modifiedData) {
            String status = String.valueOf(modifiedRow.get("status"));
            String rowId = String.valueOf(modifiedRow.get("id"));
            if ("modified".equals(status)) {
                updateDataRow(modifiedRow, rowId, source, COLUMNS);
            } else if ("deleted".equals(status)) {
                deleteRowById(source, rowId);
            }
        }

        // 新增行：客户端把它放在第几行，回发数据里的 index 就是几，服务端照着插。
        // 前面删过行时这个位置可能已经超出当前行数，按行数封顶，避免越界。
        for (Map<String, Object> modifiedRow : modifiedData) {
            if (!"newadded".equals(String.valueOf(modifiedRow.get("status")))) {
                continue;
            }

            Map<String, Object> newRow = new LinkedHashMap<>();
            // 行标识取当前最大 Id + 1（模拟数据库的自增长列）
            newRow.put("Id", getNextRowId(source));
            updateDataRow(modifiedRow, newRow, COLUMNS);

            int insertAt = source.size();
            Object index = modifiedRow.get("index");
            if (index instanceof Number number) {
                insertAt = Math.min(number.intValue(), source.size());
            }
            source.add(insertAt, newRow);
        }

        session().setAttribute(SESSION_KEY, source);

        labResult.setText("用户修改的数据：<pre>" + Json.encode(modifiedData) + "</pre>");

        bindGrid();
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    private void bindGrid() {
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    /**
     * 会话里缓存的那张可改数据表；首次访问时用演示数据填充。
     * 演示数据里省、市两列存的是名称，与省下拉列表的列表项值同源，客户端不需要再做代码到名称的翻译。
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> copy = new ArrayList<>();
            for (Map<String, Object> row : ShengShiGridData.rows()) {
                copy.add(new LinkedHashMap<>(row));
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
