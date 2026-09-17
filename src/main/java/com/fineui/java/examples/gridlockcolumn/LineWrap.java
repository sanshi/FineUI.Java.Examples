package com.fineui.java.examples.gridlockcolumn;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 列锁定 + 自动换行（路由 {@code grid-lock-column/line-wrap}）。 */
@FineUIPage("grid-lock-column/line-wrap")
public class LineWrap extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
