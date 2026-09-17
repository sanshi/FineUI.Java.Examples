package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 下拉框嵌套演示页（路由 {@code drop-down-box/nesting}）：外层下拉框的弹出面板里是「表单（性别下拉列表 + 专业下拉复选框）
 * + 表格」。性别或专业的选择改变都回发到服务端，按新条件过滤表格并重绑；外层下拉框绑定该表格，选中某行即写回。
 */
@FineUIPage("drop-down-box/nesting")
public class Nesting extends PageBase {

    DropDownBox DropDownBox1;
    Grid Grid1;
    DropDownList ddlGender;
    DropDownBox ddbMajor;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            bindGrid();
        }
    }

    private void bindGrid() {
        Grid1.setDataSource(getFilteredData(ddlGender.getSelectedValue(), ddbMajor.getValues()));
        Grid1.dataBind();
    }

    /** 按性别（{@code Gender} 相等）与专业（{@code Major} 属于所选集合）组合过滤学生数据。 */
    private List<Map<String, Object>> getFilteredData(String gender, List<String> majors) {
        boolean genderFilter = gender != null && !gender.isEmpty();
        boolean majorFilter = majors != null && !majors.isEmpty();

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> row : StudentGridData.rows()) {
            if (genderFilter && !String.valueOf(row.get("Gender")).equals(gender)) {
                continue;
            }
            if (majorFilter && !majors.contains(String.valueOf(row.get("Major")))) {
                continue;
            }
            result.add(row);
        }
        return result;
    }

    public void ddlGender_SelectedIndexChanged(Object sender, EventArgs e) {
        bindGrid();
    }

    public void ddbMajor_TextChanged(Object sender, EventArgs e) {
        bindGrid();
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownBox1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", text, String.join(", ", DropDownBox1.getValues())));
        } else {
            labResult.setText("下拉框为空");
        }
    }
}
