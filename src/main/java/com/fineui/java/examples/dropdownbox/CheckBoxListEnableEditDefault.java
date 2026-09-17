package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 下拉复选框列表（用户输入值，初始值）演示页（路由 {@code drop-down-box/check-box-list-enable-edit-default}）：
 * 首次加载时给下拉框设置一个没有对应值的初始自定义文本；提供按钮演示后台设置选中值、以及只留文本清空所有选中值。
 */
@FineUIPage("drop-down-box/check-box-list-enable-edit-default")
public class CheckBoxListEnableEditDefault extends PageBase {

    DropDownBox DropDownBox1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 设置下拉框的初始值为自定义文本（无对应选中值）
            DropDownBox1.setText("初始自定义值");
        }
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        List<String> values = DropDownBox1.getValues();
        if (values != null && !values.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", DropDownBox1.getText(), String.join(", ", values)));
        } else {
            labResult.setText(String.format("用户输入值：%s", DropDownBox1.getText()));
        }
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        // 后台更新下拉框的值，需要同时设置文本和值
        DropDownBox1.setTexts(Arrays.asList("PHP", "Basic"));
        DropDownBox1.setValues(Arrays.asList("php", "basic"));
    }

    public void btnSetText_Click(Object sender, EventArgs e) {
        // 只更新文本、清空所有选中值（值置为空数组）
        DropDownBox1.setText("用户输入值");
        DropDownBox1.setValues(new ArrayList<>());
    }
}
