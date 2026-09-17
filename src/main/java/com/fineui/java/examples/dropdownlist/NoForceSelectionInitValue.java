package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 用户输入值（带初始值）演示页（路由 {@code drop-down-list/no-force-selection-init-value}）：AutoSelectFirstItem=false、
 * EnableEdit=true、ForceSelection=false，声明初始显示文本「初始自定义值」。可服务端选中列表项、或把显示文本设为用户输入值。
 */
@FineUIPage("drop-down-list/no-force-selection-init-value")
public class NoForceSelectionInitValue extends PageBase {

    DropDownList DropDownList1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("Value6");
    }

    public void btnSetText_Click(Object sender, EventArgs e) {
        DropDownList1.setText("用户输入值");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String value = DropDownList1.getSelectedValue();
        if (value != null && !value.isEmpty()) {
            labResult.setText(String.format("选中项：%s（值：%s）", DropDownList1.getText(), value));
        } else {
            labResult.setText(String.format("用户输入值：%s", DropDownList1.getText()));
        }
    }
}
