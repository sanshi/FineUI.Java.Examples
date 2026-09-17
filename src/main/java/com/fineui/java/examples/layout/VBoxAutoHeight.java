package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * VBox 自动高度布局演示页（路由 {@code layout/vbox-auto-height}）：外层面板未指定高度，按 VBox 纵向排列子面板并
 * 随内容自动撑高，展示子面板内容变多时整体高度自适应增长的效果。
 */
@FineUIPage("layout/vbox-auto-height")
public class VBoxAutoHeight extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
