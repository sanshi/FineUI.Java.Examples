package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 下拉单选框列表（用户输入值）演示页（路由 {@code drop-down-box/radio-button-list-enable-edit}）：下拉框开启
 * {@code enable-edit}，用户可在输入框直接键入自定义文本。回发时若有对应的值则视为选中项，否则视为用户自输入值。
 */
@FineUIPage("drop-down-box/radio-button-list-enable-edit")
public class RadioButtonListEnableEdit extends PageBase {

    DropDownBox DropDownBox1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String value = DropDownBox1.getValue();
        if (value != null && !value.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", DropDownBox1.getText(), value));
        } else {
            labResult.setText(String.format("用户输入值：%s", DropDownBox1.getText()));
        }
    }
}
