package com.fineui.java.examples.gridnav;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 显示选中单元格（不显示选中行）（路由 {@code grid-nav/show-selected-row}）。 */
@FineUIPage("grid-nav/show-selected-row")
public class ShowSelectedRow extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
