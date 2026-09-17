package com.fineui.java.examples.gridother;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/** 分页（自定义翻页图标）（路由 {@code grid-other/grid-paging-custom-icon}）。 */
@FineUIPage("grid-other/grid-paging-custom-icon")
public class GridPagingCustomIcon extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }
}
