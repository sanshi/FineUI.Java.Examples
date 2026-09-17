package com.fineui.java.examples.gridnav;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/** 内存分页（快速切换分页）（路由 {@code grid-nav/quick-paging}）。 */
@FineUIPage("grid-nav/quick-paging")
public class QuickPaging extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }
}
