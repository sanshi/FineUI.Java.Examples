package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.enums.IconFont;
import com.fineui.java.examples.code.PageBase;

/**
 * 按钮演示页（路由 {@code button/button}）：展示按钮的颜色、大小、启用/禁用、按下状态与提示信息，
 * 并演示在服务端事件里改这些属性后由框架增量推回客户端（无整页刷新）。
 */
@FineUIPage("button/button")
public class Button extends PageBase {

    com.fineui.java.core.controls.Button btnPrimary;
    com.fineui.java.core.controls.Button btnEnable;
    com.fineui.java.core.controls.Button btnPressed;
    com.fineui.java.core.controls.Button btnTooltip;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            btnPrimary.setIconFont(IconFont.Tag);
        }
    }

    public void btnEnable_Click(Object sender, EventArgs e) {
        showNotify("你点击了刚刚启用的按钮");
    }

    public void btnChangeEnable_Click(Object sender, EventArgs e) {
        btnEnable.setEnabled(true);
        btnEnable.setText("本按钮已经启用（点击弹出对话框）");
    }

    public void btnChangePressed_Click(Object sender, EventArgs e) {
        btnPressed.setPressed(!btnPressed.isPressed());
    }

    public void btnTooltip_Click(Object sender, EventArgs e) {
        btnTooltip.setToolTip("这是改变后的提示信息");
    }
}
