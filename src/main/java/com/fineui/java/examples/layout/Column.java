package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 列布局演示页（路由 {@code layout/column}）：面板采用 Column 布局，子面板通过 ColumnWidth（百分比）按列分配宽度，
 * 并演示用 HBox 布局实现相同界面的推荐做法。
 */
@FineUIPage("layout/column")
public class Column extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
