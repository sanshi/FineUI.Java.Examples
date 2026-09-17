package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 用户输入值下拉列表演示页（路由 {@code drop-down-list/no-force-selection}）：EnableEdit=true、ForceSelection=false，
 * 允许保留用户手工输入的文本（此时无匹配选中项）。「获取选中项」区分「选中项 / 用户输入值 / 无选中项」三种展示。
 */
@FineUIPage("drop-down-list/no-force-selection")
public class NoForceSelection extends PageBase {

    DropDownList DropDownList1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("Value6");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String value = DropDownList1.getSelectedValue();
        if (value != null && !value.isEmpty()) {
            labResult.setText(String.format("选中项：%s（值：%s）", DropDownList1.getText(), value));
        } else {
            String text = DropDownList1.getText();
            labResult.setText((text == null || text.isEmpty()) ? "无选中项" : String.format("用户输入值：%s", text));
        }
    }
}
