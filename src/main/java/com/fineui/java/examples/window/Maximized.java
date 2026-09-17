package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体最大化演示页（路由 {@code window/maximized}）：窗体设置了 {@code maximized="true"}，
 * 每次显示（含关闭后重新显示）都默认最大化。
 */
@FineUIPage("window/maximized")
public class Maximized extends PageBase {

    com.fineui.java.core.controls.Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
    }
}
