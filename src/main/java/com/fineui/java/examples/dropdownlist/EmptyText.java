package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 初始为空下拉列表演示页（路由 {@code drop-down-list/empty-text}）：AutoSelectFirstItem=false + EmptyText 占位提示，
 * 初始无选中显示占位符；含不可选项（EnableSelect=false）。「获取选中项」按显示文本判断有无选中。
 */
@FineUIPage("drop-down-list/empty-text")
public class EmptyText extends PageBase {

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
