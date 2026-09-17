package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * HBox 布局横向滚动条演示页（路由 {@code layout/hbox-scroll-bar}）：面板采用 HBox 布局并开启
 * AutoScroll，当页面宽度变小、子面板总宽超出容器时出现横向滚动条。面板铺满视口（IsViewPort=true）。
 */
@FineUIPage("layout/hbox-scroll-bar")
public class HBoxScrollBar extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
