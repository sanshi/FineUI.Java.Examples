package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体右下角固定演示页（路由 {@code window/bottom-right}）：通过页面级 CSS 把窗体固定到浏览器右下角，
 * 并禁止拖动（{@code enable-drag="false"}）。
 */
@FineUIPage("window/bottom-right")
public class BottomRight extends PageBase {

    com.fineui.java.core.controls.Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
    }
}
