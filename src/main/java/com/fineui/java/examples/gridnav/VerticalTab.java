package com.fineui.java.examples.gridnav;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 键盘导航（Tab纵向导航）（路由 {@code grid-nav/vertical-tab}）。 */
@FineUIPage("grid-nav/vertical-tab")
public class VerticalTab extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
