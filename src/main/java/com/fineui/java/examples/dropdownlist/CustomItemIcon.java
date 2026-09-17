package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/** 下拉列表项图标演示页（路由 {@code drop-down-list/custom-item-icon}）：用列表项 display 为每项加国旗图标。 */
@FineUIPage("drop-down-list/custom-item-icon")
public class CustomItemIcon extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            for (int i = 0; i < DropDownList1.getItemCount(); i++) {
                String value = DropDownList1.getItemValue(i);
                DropDownList1.setItemDisplay(i, "<img src=\"/res/icon/flag_" + value + ".png\">&nbsp;" + DropDownList1.getItemText(i));
            }
        }
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("au");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownList1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText("选中项：" + text + "（值：" + DropDownList1.getSelectedValue() + "）");
        } else {
            labResult.setText("无选中项");
        }
    }
}
