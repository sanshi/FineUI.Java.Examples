package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 行内块布局演示页（路由 {@code layout/inline-block}）：外层面板铺满视口（IsViewPort=true）并开启 AutoScroll，
 * 内部子面板通过自定义 CssClass（display:inline-block）实现行内块排列、自动换行。
 */
@FineUIPage("layout/inline-block")
public class InlineBlock extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
