package com.fineui.java.examples.gridrowheight;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 行密度（自动换行，多行文本省略）（路由 {@code grid-row-height/grid-row-density-line-wrap-line-clamp}）。 */
@FineUIPage("grid-row-height/grid-row-density-line-wrap-line-clamp")
public class GridRowDensityLineWrapLineClamp extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
