package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * VBox 滚动条布局演示页（路由 {@code layout/vbox-scroll-bar}）：外层面板铺满视口按 VBox 纵向排列子面板并开启
 * AutoScroll，当视口高度变小、子面板受 MinHeight 约束无法继续压缩时，面板内出现纵向滚动条。
 */
@FineUIPage("layout/vbox-scroll-bar")
public class VBoxScrollBar extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
