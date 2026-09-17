package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 选中项改变事件 + 可编辑演示页（路由 {@code drop-down-list/selected-index-changed-enable-edit}）：EnableEdit=true
 * （强制选择），下拉选择或输入匹配后失焦均触发 OnSelectedIndexChanged 回发。
 */
@FineUIPage("drop-down-list/selected-index-changed-enable-edit")
public class SelectedIndexChangedEnableEdit extends PageBase {

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
        String text = DropDownList1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("选中项：%s（值：%s）", text, DropDownList1.getSelectedValue()));
        } else {
            labResult.setText("无选中项");
        }
    }
}
