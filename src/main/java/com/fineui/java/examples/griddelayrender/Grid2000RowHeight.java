package com.fineui.java.examples.griddelayrender;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 2000 行（行高固定）（路由 {@code grid-delay-render/grid2000-row-height}）。 */
@FineUIPage("grid-delay-render/grid2000-row-height")
public class Grid2000RowHeight extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rowsN(1980));
            Grid1.dataBind();
        }
    }
}
