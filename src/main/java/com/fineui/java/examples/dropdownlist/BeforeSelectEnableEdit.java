package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 选中前确认 + 可编辑演示页（路由 {@code drop-down-list/before-select-enable-edit}）：EnableEdit=true，beforeselect 监听里
 * 用 window.confirm 询问，取消则还原选中项。「获取选中项」回发读取当前选中项。
 */
@FineUIPage("drop-down-list/before-select-enable-edit")
public class BeforeSelectEnableEdit extends PageBase {

    DropDownList DropDownList1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("Value6");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        // 本页无条件展示（不判断是否有选中项）——与本示例的原始行为一致
        labResult.setText(String.format("选中项：%s（值：%s）", DropDownList1.getText(), DropDownList1.getSelectedValue()));
    }
}
