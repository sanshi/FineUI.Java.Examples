package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentData;

/** 下拉列表自定义项演示页（路由 {@code drop-down-list/custom-item-group-data-display-fields-multi-select}）。 */
@FineUIPage("drop-down-list/custom-item-group-data-display-fields-multi-select")
public class CustomItemGroupDataDisplayFieldsMultiSelect extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            DropDownList1.setEnableMultiSelect(true);
            DropDownList1.setEnableGroup(true);
            DropDownList1.setEnableCheckBoxSelect(true);
            for (StudentData.Student s : StudentData.STUDENTS) {
                DropDownList1.addListItem(s.id(), s.name(), true);
                int i = DropDownList1.getItemCount() - 1;
                DropDownList1.setItemGroup(i, s.major());
                DropDownList1.setItemDisplay(i, "<div class=\"item-text\">" + s.name() + "</div><div class=\"item-desc\">" + s.desc() + "</div>");
            }
        }
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownList1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText("选中项文本：" + text + "<br/>选中项值：" + String.join(", ", DropDownList1.getSelectedValues()));
        } else {
            labResult.setText("无选中项");
        }
    }
}
