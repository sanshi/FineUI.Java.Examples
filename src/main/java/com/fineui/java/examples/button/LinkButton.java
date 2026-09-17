package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 链接按钮演示页（路由 {@code button/link-button}）：客户端事件（标签里用 {@code click-handler} 填页面脚本区的具名函数名）、
 * 服务器端事件（回发）、初始禁用的链接按钮；点击按钮启用/禁用最后一个链接按钮。
 */
@FineUIPage("button/link-button")
public class LinkButton extends PageBase {

    com.fineui.java.core.controls.LinkButton LinkButton1;

    public void LinkButton3_Click(Object sender, EventArgs e) {
        showNotify("这是服务器端事件");
    }

    public void btnChangeEnable_Click(Object sender, EventArgs e) {
        LinkButton1.setEnabled(!LinkButton1.isEnabled());
    }
}
