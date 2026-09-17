package com.fineui.java.examples.griddelayrender;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 1000 行（行高固定，延迟渲染）（路由 {@code grid-delay-render/grid1000-row-height-delay-render}）。 */
@FineUIPage("grid-delay-render/grid1000-row-height-delay-render")
public class Grid1000RowHeightDelayRender extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rowsN(996));
            Grid1.dataBind();
        }
    }
}
