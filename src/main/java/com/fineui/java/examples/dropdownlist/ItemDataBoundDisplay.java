package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.ListItemEventArgs;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentData;

/** 下拉列表自定义项演示页（路由 {@code drop-down-list/item-data-bound-display}）。 */
@FineUIPage("drop-down-list/item-data-bound-display")
public class ItemDataBoundDisplay extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            DropDownList1.setDataTextField("name");
            DropDownList1.setDataValueField("id");
            DropDownList1.setDataSource(StudentData.STUDENTS);
            DropDownList1.dataBind();
        }
    }

    public void DropDownList1_ItemDataBound(Object sender, ListItemEventArgs e) {
        StudentData.Student s = (StudentData.Student) e.getDataItem();

        e.getItem().setDisplay("<div class=\"item-text\">" + s.name() + "（" + s.id() + "）</div><div class=\"item-desc\">" + s.desc() + "</div>");
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
