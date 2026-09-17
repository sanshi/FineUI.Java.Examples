package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 下拉列表多选演示页（路由 {@code drop-down-list/multi-select-no-force-selection}）。
 */
@FineUIPage("drop-down-list/multi-select-no-force-selection")
public class MultiSelectNoForceSelection extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("Value6");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        if (!DropDownList1.getSelectedValues().isEmpty()) {
            labResult.setText("选中项文本：" + DropDownList1.getText() + "<br/>选中项值：" + String.join(", ", DropDownList1.getSelectedValues()));
        } else {
            labResult.setText("用户输入值：" + DropDownList1.getText());
        }
    }
}
