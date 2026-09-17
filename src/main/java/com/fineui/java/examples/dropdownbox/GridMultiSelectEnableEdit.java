package com.fineui.java.examples.dropdownbox;

import java.util.List;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 下拉表格（多选，用户输入值）演示页（路由 {@code drop-down-box/grid-multi-select-enable-edit}）：多选下拉表格
 * {@code enable-edit=true} 允许用户自行输入。回发时若有选中值集合说明是从表格选的，否则是用户输入的文本。
 */
@FineUIPage("drop-down-box/grid-multi-select-enable-edit")
public class GridMultiSelectEnableEdit extends PageBase {

    DropDownBox DropDownBox1;
    com.fineui.java.core.controls.Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        List<String> values = DropDownBox1.getValues();
        if (values != null && !values.isEmpty()) {
            // 有选中值集合 → 从表格中选中的
            labResult.setText(String.format("下拉框文本：%s（值：%s）", DropDownBox1.getText(), String.join(", ", values)));
        } else {
            // 无选中值 → 用户手工输入的文本
            labResult.setText(String.format("用户输入值：%s", DropDownBox1.getText()));
        }
    }
}
