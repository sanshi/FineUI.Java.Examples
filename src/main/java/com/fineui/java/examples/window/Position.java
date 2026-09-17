package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体定位示例（路由 {@code window/position}）：演示 9 个窗体在
 * {@code position-x}（Left/Center/Right）× {@code position-y}（Top/Center/Bottom）各组合下的位置，
 * 且 {@code depends-view-port-size="false"} 使位置不随视口大小变化。
 */
@FineUIPage("window/position")
public class Position extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
