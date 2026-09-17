package com.fineui.java.examples.gridrowheight;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 100 行（行高不同，列锁定）（路由 {@code grid-row-height/grid100-lock-column}）：108 行数据（12 行 × 9 组）。 */
@FineUIPage("grid-row-height/grid100-lock-column")
public class Grid100LockColumn extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows100());
            Grid1.dataBind();
        }
    }
}
