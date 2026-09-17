package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义下拉框演示页（路由 {@code drop-down-box/themes}）：下拉框的弹出面板是一个内容面板，里面放主题缩略图网格（原生 HTML）。
 * 因数据来源自定义（CustomData=true），初始值须同时给出值与文本；点击缩略图由客户端脚本把主题名/标题写回下拉框并收起面板。
 */
@FineUIPage("drop-down-box/themes")
public class Themes extends PageBase {

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
