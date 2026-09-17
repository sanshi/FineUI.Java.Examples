package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体最小/最大尺寸演示页（路由 {@code window/min-width}）：窗体初始 400px、最小 300px、最大 600px，
 * 拖动缩放时受最小/最大尺寸约束。
 */
@FineUIPage("window/min-width")
public class MinWidth extends PageBase {

    com.fineui.java.core.controls.Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
    }
}
