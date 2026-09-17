package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * HBox 布局横向滚动条（自适应高度）演示页（路由 {@code layout/hbox-scroll-bar-auto-height}）：面板采用
 * HBox 布局并开启 AutoScroll、高度自适应（IsFluid=true）。反复缩小页面宽度使面板出现横向滚动条时，面板高度应保持不变。
 */
@FineUIPage("layout/hbox-scroll-bar-auto-height")
public class HBoxScrollBarAutoHeight extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
