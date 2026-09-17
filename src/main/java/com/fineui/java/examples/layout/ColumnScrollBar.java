package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 列布局纵向滚动条演示页（路由 {@code layout/column-scroll-bar}）：面板采用 Column 布局、铺满视口
 * （IsViewPort=true）并开启 AutoScroll，当高度变小、内容超出容器时出现纵向滚动条。
 */
@FineUIPage("layout/column-scroll-bar")
public class ColumnScrollBar extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
