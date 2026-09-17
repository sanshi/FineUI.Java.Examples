package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.Arrays;
import java.util.List;

/**
 * 下拉复选框列表（用户输入值）演示页（路由 {@code drop-down-box/check-box-list-enable-edit}）：下拉框多选并开启
 * {@code enable-edit}，用户可在输入框直接键入自定义文本。回发时若有选中值则视为选中项，否则视为用户自输入值。
 */
@FineUIPage("drop-down-box/check-box-list-enable-edit")
public class CheckBoxListEnableEdit extends PageBase {

    DropDownBox DropDownBox1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
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
}
