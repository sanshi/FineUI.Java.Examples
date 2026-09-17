package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * VBox 嵌套 HBox 布局演示页（路由 {@code layout/vbox-hbox}）：外层面板铺满视口按 VBox 纵向分为两行，
 * 每行是一个 HBox 面板横向平分三个子面板，改变页面大小可观察子面板随之自适应变化。
 */
@FineUIPage("layout/vbox-hbox")
public class VBoxHBox extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
