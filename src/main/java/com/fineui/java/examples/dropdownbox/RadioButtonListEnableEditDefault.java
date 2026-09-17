package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 下拉单选框列表（用户输入值，初始值）演示页（路由 {@code drop-down-box/radio-button-list-enable-edit-default}）：
 * 首次加载时给下拉框设置一个没有对应值的初始自定义文本；提供按钮演示后台同时设置值+文本、以及只留文本清空值。
 */
@FineUIPage("drop-down-box/radio-button-list-enable-edit-default")
public class RadioButtonListEnableEditDefault extends PageBase {

    DropDownBox DropDownBox1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 设置下拉框的初始值为自定义文本（无对应选中值）
            DropDownBox1.setText("初始自定义值");
        }
    }

    public void btnSelectItem_Click(Object sender, EventArgs e) {
        // 后台同时设置值和文本，选中 JavaScript
        DropDownBox1.setValue("js");
        DropDownBox1.setText("JavaScript");
    }

    public void btnSetText_Click(Object sender, EventArgs e) {
        // 只更新文本、清空值（值显式置 null）
        DropDownBox1.setText("用户输入值");
        DropDownBox1.setValue((String) null);
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
