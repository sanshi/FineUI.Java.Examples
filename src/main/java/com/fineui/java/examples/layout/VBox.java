package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * VBox 布局演示页（路由 {@code layout/vbox}）：面板按 VBox 纵向排列子面板，逐块展示 BoxConfigAlign（交叉轴对齐）
 * 与 BoxConfigPosition（主轴对齐）的各种取值组合，以及 BoxFlex / Height / Width / Margin 对子项尺寸的影响。
 */
@FineUIPage("layout/vbox")
public class VBox extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
