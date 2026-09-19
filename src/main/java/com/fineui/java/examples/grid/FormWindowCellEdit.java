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
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 表格与表单（弹出窗体，表格中保存）：数据在浏览器里改，改完一次性提交。
 *
 * <p>单元格直接改、或从弹出窗体的表单里「保存到表格」，都只是改浏览器里那份表格数据；点表格外面的「全部保存」
 * 才回发，服务端把这一批改动（修改 / 删除 / 新增）落到会话里的数据表，再重新绑定表格。
 *
 * <p>新增行的位置由客户端在回发数据里带上来（{@code index}），所以服务端能把它插回原来的位置——前提是表格
 * 不分页、也没在客户端排过序，否则客户端看到的行序和数据源的行序对不上，插进去的位置就是错的。
 *
 * <p>数据存在会话里，模拟一张能改的数据表。真实项目不要在会话里放大量数据，否则会严重影响服务器性能。
 */
@FineUIPage("grid/form-window-cell-edit")
public class FormWindowCellEdit extends PageBase {

    private static final String SESSION_KEY = "Grid.FormWindowCellEdit";
    private static final String[] COLUMNS = {"Name", "Gender", "EntranceYear", "EntranceDate", "AtSchool", "Major"};

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

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            bindGrid();
        }
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

    /** 会话里缓存的那张可改数据表；首次访问时用演示数据填充。 */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> copy = new ArrayList<>();
            for (Map<String, Object> row : StudentGridData.rows()) {
                copy.add(new LinkedHashMap<>(row));
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
