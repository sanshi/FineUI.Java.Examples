package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义按钮演示页（路由 {@code button/button-custom}）：普通大按钮，以及用背景图片自定义外观的按钮；
 * 两者点击都在服务端弹出通知。
 */
@FineUIPage("button/button-custom")
public class ButtonCustom extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showNotify("点击了普通按钮");
    }

    public void Button2_Click(Object sender, EventArgs e) {
        showNotify("点击了自定义按钮");
    }
}
