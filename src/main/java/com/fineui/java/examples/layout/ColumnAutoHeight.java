package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 列布局（自适应高度）演示页（路由 {@code layout/column-auto-height}）：面板采用 Column 布局、高度自适应
 * （IsFluid=true）并开启 AutoScroll，每列内堆叠多个可折叠面板，整体高度随内容自动增长。
 */
@FineUIPage("layout/column-auto-height")
public class ColumnAutoHeight extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
