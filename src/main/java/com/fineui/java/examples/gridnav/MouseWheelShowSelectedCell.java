package com.fineui.java.examples.gridnav;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 鼠标滚轮（选中单元格）（路由 {@code grid-nav/mouse-wheel-show-selected-cell}）。 */
@FineUIPage("grid-nav/mouse-wheel-show-selected-cell")
public class MouseWheelShowSelectedCell extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
