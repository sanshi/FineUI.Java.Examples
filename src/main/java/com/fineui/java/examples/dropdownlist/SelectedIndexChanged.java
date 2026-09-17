package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 选中项改变事件演示页（路由 {@code drop-down-list/selected-index-changed}）：下拉选择改变即触发 OnSelectedIndexChanged
 * 回发更新结果；服务端 setSelectedValue 只更新显示、不触发选中项改变事件。
 */
@FineUIPage("drop-down-list/selected-index-changed")
public class SelectedIndexChanged extends PageBase {

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
