package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 菜单按钮（MenuID 属性）演示页（路由 {@code button/button-menu-id}）：先声明一个独立菜单，
 * 再由多个按钮通过 {@code menu-id} 共享引用同一个菜单。
 */
@FineUIPage("button/button-menu-id")
public class ButtonMenuID extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
