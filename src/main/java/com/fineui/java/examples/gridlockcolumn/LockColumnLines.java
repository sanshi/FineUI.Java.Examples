package com.fineui.java.examples.gridlockcolumn;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 列锁定 + 列分割线（路由 {@code grid-lock-column/lock-column-lines}）。 */
@FineUIPage("grid-lock-column/lock-column-lines")
public class LockColumnLines extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
