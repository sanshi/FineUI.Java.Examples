package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * HBox 自动高度拉伸布局演示页（路由 {@code layout/hbox-auto-height-stretch}）：多个未指定高度的 HBox 面板，
 * 对比 BoxConfigAlign 取默认 Stretch（子面板高度被拉伸对齐到最高项）与取 Start（子面板各自按内容高度顶部对齐）的差异。
 */
@FineUIPage("layout/hbox-auto-height-stretch")
public class HBoxAutoHeightStretch extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
