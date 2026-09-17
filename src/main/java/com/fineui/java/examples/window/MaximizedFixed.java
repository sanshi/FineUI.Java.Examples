package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体固定最大化演示页（路由 {@code window/maximized-fixed}）：窗体 {@code maximized="true"} 且
 * {@code enable-maximize="false"}，始终以最大化状态显示且不提供还原按钮。
 */
@FineUIPage("window/maximized-fixed")
public class MaximizedFixed extends PageBase {

    com.fineui.java.core.controls.Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
    }
}
