package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * HBox 自动高度布局演示页（路由 {@code layout/hbox-auto-height}）：外层面板未指定高度，按 HBox 横向排列子面板并
 * 随最高子面板内容自动撑高，同时开启 AutoScroll，子面板总宽超出时出现横向滚动条。
 */
@FineUIPage("layout/hbox-auto-height")
public class HBoxAutoHeight extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
