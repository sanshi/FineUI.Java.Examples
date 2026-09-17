package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 下拉单选框列表（MatchFieldWidth）演示页（路由 {@code drop-down-box/radio-button-list-match-field-width}）：
 * 下拉框设置 {@code match-field-width="false"}，弹出面板宽度不随输入框、改由面板自身宽度决定（可放多列列表）。
 */
@FineUIPage("drop-down-box/radio-button-list-match-field-width")
public class RadioButtonListMatchFieldWidth extends PageBase {

    DropDownBox DropDownBox1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownBox1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", text, DropDownBox1.getValue()));
        } else {
            labResult.setText("下拉框为空");
        }
    }
}
