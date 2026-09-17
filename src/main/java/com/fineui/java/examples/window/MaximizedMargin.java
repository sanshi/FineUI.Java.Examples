package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体最大化外边距演示页（路由 {@code window/maximized-margin}）：通过页面级 CSS 给最大化状态的窗体
 * 加上 50px 外边距，使最大化后四周留白。
 */
@FineUIPage("window/maximized-margin")
public class MaximizedMargin extends PageBase {

    com.fineui.java.core.controls.Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
    }
}
