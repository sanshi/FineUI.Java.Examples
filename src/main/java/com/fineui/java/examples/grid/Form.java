package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.RadioButtonList;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 表格与表单：左边表格、右边表单。点选表格行即把该行数据填进表单，表单底部工具栏负责新增 / 删除 / 保存。
 *
 * <p>「新增」是纯客户端动作——重置表单、取消行选中、把保存按钮改回新增语义；表单的隐藏字段里存着当前编辑行的
 * 行标识，所以保存时按它是否为空区分新增与编辑：为空就是新增（行标识取当前最大 Id + 1，模拟数据库的自增长列），
 * 否则就是编辑。保存与删除后都重新绑定表格，并把刚保存的那一行重新选中。
 *
 * <p>数据存在会话里，模拟一张能改的数据表。真实项目不要在会话里放大量数据，否则会严重影响服务器性能。
 */
@FineUIPage("grid/form")
public class Form extends PageBase {

    private static final String SESSION_KEY = "Grid.Form";

    Grid Grid1;
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

    /** 删除表单当前对应的那一行，然后重新绑定表格并模拟点击「新增按钮」，让表单回到新增状态。 */
    public void btnDelete_Click(Object sender, EventArgs e) {
        String rowId = hfFormID.getText();
        if (!rowId.isEmpty()) {
            deleteRowById(sourceData(), rowId);
        }

        bindGrid();
        invokeClientFunction("onNewButtonClick");
    }

    /** 保存表单：隐藏字段里的行标识为空即新增、否则为编辑；保存后重新绑定并选中这一行。 */
    public void btnSave_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> source = sourceData();

        String rowId = hfFormID.getText();
        Map<String, Object> rowData;
        if (rowId.isEmpty()) {
            // 新增：行标识取当前最大 Id + 1（模拟数据库的自增长列）
            rowData = new LinkedHashMap<>();
            rowData.put("Id", getNextRowId(source));
            source.add(rowData);
        } else {
            // 编辑
            rowData = findRowById(source, rowId);
            if (rowData == null) {
                // 会话里的数据被别的页面改过（比如刚被删除）时会走到这里，此时按新增处理会把行标识算错，故直接提示
                showNotify("要保存的数据已不存在，请重新选择！", MessageBoxIcon.Error);
                return;
            }
        }

        // 姓名
        rowData.put("Name", tbxFormUserName.getValue().trim());
        // 性别（1 男 / 0 女）
        rowData.put("Gender", Integer.parseInt(rblFormGender.getSelectedValue()));
        // 入学年份
        rowData.put("EntranceYear", Integer.parseInt(nbFormEntranceYear.getValue()));
        // 入学日期
        rowData.put("EntranceDate", dpFormEntranceDate.getValue());
        // 是否在校
        rowData.put("AtSchool", cbFormAtSchool.isChecked());
        // 所学专业
        rowData.put("Major", ddlFormMajor.getSelectedValue());

        session().setAttribute(SESSION_KEY, source);

        // 重新绑定表格，并选中刚保存的这一行（回发里调用会下发按行标识选中的命令）
        bindGrid();
        Grid1.setSelectedRowIdArray(new String[] {String.valueOf(rowData.get("Id"))});
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
