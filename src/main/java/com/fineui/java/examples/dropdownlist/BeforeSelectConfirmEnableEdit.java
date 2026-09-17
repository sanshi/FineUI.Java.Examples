package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 选中前确认（F.confirm）+ 可编辑演示页（路由 {@code drop-down-list/before-select-confirm-enable-edit}）：EnableEdit=true，
 * beforeselect 监听里用异步 F.confirm 询问，确定则重新设值、取消则还原。「获取选中项」回发读取当前选中项。
 */
@FineUIPage("drop-down-list/before-select-confirm-enable-edit")
public class BeforeSelectConfirmEnableEdit extends PageBase {

    DropDownList DropDownList1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("Value6");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownList1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("选中项：%s（值：%s）", text, DropDownList1.getSelectedValue()));
        } else {
            labResult.setText("无选中项");
        }
    }
}
