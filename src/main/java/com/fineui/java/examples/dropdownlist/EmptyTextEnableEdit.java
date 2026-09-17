package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 初始为空 + 可编辑演示页（路由 {@code drop-down-list/empty-text-enable-edit}）：AutoSelectFirstItem=false + EmptyText
 * 占位提示 + EnableEdit=true（强制选择），初始无选中，可服务端选中、也可输入文本过滤匹配。
 */
@FineUIPage("drop-down-list/empty-text-enable-edit")
public class EmptyTextEnableEdit extends PageBase {

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
