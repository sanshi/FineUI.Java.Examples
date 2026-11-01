package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/** 可编辑下拉列表清空后，核对选中值和自由文字的赋值顺序及下一轮回发结果。 */
@FineUIPage("form/drop-down-list-assignment-order")
public class DropDownListAssignmentOrder extends PageBase {

    DropDownList DropDownList2;
    Label labResult;

    public void btnValueThenText_Click(Object sender, EventArgs e) {
        DropDownList2.clearSelection();
        DropDownList2.setSelectedValue("a");
        DropDownList2.setText("自定义输入");
        showAssignmentValue();
    }

    public void btnTextThenValue_Click(Object sender, EventArgs e) {
        DropDownList2.clearSelection();
        DropDownList2.setText("自定义输入");
        DropDownList2.setSelectedValue("a");
        showAssignmentValue();
    }

    private void showAssignmentValue() {
        String value = DropDownList2.getSelectedValue();
        labResult.setText("赋值后选中值：" + (value == null ? "空" : value));
    }

    public void btnReadDropDownList_Click(Object sender, EventArgs e) {
        String value = DropDownList2.getSelectedValue();
        labResult.setText("选中值：" + (value == null ? "空" : value) + "；文字：" + DropDownList2.getText());
    }
}
