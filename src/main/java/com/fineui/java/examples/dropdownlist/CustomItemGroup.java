package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentData;

/** 下拉列表自定义项演示页（路由 {@code drop-down-list/custom-item-group}）。 */
@FineUIPage("drop-down-list/custom-item-group")
public class CustomItemGroup extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            DropDownList1.setEnableGroup(true);
            for (StudentData.Student s : StudentData.STUDENTS) {
                DropDownList1.addListItem(s.id(), s.name(), true);
                DropDownList1.setItemGroup(DropDownList1.getItemCount() - 1, s.major());
            }
        }
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
