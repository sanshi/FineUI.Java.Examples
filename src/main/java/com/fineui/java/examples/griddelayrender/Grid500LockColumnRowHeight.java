package com.fineui.java.examples.griddelayrender;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 500 行（行高固定，列锁定）（路由 {@code grid-delay-render/grid500-lock-column-rowheight}）。 */
@FineUIPage("grid-delay-render/grid500-lock-column-rowheight")
public class Grid500LockColumnRowHeight extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rowsN(504));
            Grid1.dataBind();
        }
    }
}
