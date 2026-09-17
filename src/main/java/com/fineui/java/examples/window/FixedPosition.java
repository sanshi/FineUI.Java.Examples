package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体固定定位示例（路由 {@code window/fixed-position}）：演示 9 个窗体在
 * {@code position-x}（Left/Center/Right）× {@code position-y}（Top/Center/Bottom）各组合下的位置，
 * 且 {@code fixed-position="true"} 使窗体固定定位、不随页面滚动。
 */
@FineUIPage("window/fixed-position")
public class FixedPosition extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
