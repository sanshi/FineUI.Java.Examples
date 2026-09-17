package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 选中项改变事件 + 可编辑 + 用户输入值演示页（路由 {@code drop-down-list/selected-index-changed-enable-edit-no-force-selection}）：
 * EnableEdit=true、ForceSelection=false，输入任意值失焦也回发（无匹配时 SelectedValue 为空、展示「用户输入值」）。
 */
@FineUIPage("drop-down-list/selected-index-changed-enable-edit-no-force-selection")
public class SelectedIndexChangedEnableEditNoForceSelection extends PageBase {

    DropDownList DropDownList1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("Value6");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        showResult();
    }

    public void DropDownList1_SelectedIndexChanged(Object sender, EventArgs e) {
        showResult();
    }

    private void showResult() {
        String value = DropDownList1.getSelectedValue();
        if (value != null && !value.isEmpty()) {
            labResult.setText(String.format("选中项：%s（值：%s）", DropDownList1.getText(), value));
        } else {
            labResult.setText(String.format("用户输入值：%s", DropDownList1.getText()));
        }
    }
}
