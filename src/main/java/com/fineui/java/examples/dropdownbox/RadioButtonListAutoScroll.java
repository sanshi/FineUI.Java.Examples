package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 下拉单选框列表（滚动条）演示页（路由 {@code drop-down-box/radio-button-list-auto-scroll}）：弹出面板内放一个
 * 项数较多的 RadioButtonList，面板开启自动滚动条；「获取下拉框的选中值」回发读取下拉框当前文本与值。
 */
@FineUIPage("drop-down-box/radio-button-list-auto-scroll")
public class RadioButtonListAutoScroll extends PageBase {

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
