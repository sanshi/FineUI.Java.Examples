package com.fineui.java.examples.gridrowgroup;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 行分组（分组合计，单元格样式）（路由 {@code grid-row-group/summary-renderer}）。 */
@FineUIPage("grid-row-group/summary-renderer")
public class SummaryRenderer extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
