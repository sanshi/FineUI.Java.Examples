package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

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
            DropDownBox1.setUserInput("初始自定义值");
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
        DropDownBox1.setSelection(
                List.of("php", "basic"),
                List.of("PHP", "Basic"));
    }

    public void btnSetText_Click(Object sender, EventArgs e) {
        DropDownBox1.setUserInput("用户输入值");
    }

    public void btnSetCommaText_Click(Object sender, EventArgs e) {
        // 逗号属于用户输入的文字，不能拆成两个选项。
        DropDownBox1.setUserInput("研发, 华东");
    }

    public void btnClearSelection_Click(Object sender, EventArgs e) {
        DropDownBox1.clearSelection();
    }
}
