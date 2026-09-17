package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/** 下拉列表多选、可编辑、非强制选择、带初始用户输入值演示页（路由 {@code drop-down-list/no-force-selection-init-value-multi-select}）。 */
@FineUIPage("drop-down-list/no-force-selection-init-value-multi-select")
public class NoForceSelectionInitValueMultiSelect extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValues(List.of("Value4", "Value6"));
    }

    public void btnSetText_Click(Object sender, EventArgs e) {
        DropDownList1.setText("用户输入值");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        if (!DropDownList1.getSelectedValues().isEmpty()) {
            labResult.setText("选中项文本：" + DropDownList1.getText() + "<br/>选中项值：" + String.join(", ", DropDownList1.getSelectedValues()));
        } else {
            labResult.setText("用户输入值：" + DropDownList1.getText());
        }
    }
}
