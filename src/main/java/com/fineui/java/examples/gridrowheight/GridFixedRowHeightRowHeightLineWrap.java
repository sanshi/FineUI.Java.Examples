package com.fineui.java.examples.gridrowheight;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 行高固定（手工设置行高，自动换行）（路由 {@code grid-row-height/grid-fixed-row-height-row-height-line-wrap}）。 */
@FineUIPage("grid-row-height/grid-fixed-row-height-row-height-line-wrap")
public class GridFixedRowHeightRowHeightLineWrap extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
