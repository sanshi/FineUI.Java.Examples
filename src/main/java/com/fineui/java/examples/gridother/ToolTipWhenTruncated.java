package com.fineui.java.examples.gridother;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 单元格提示（仅截断时显示）（路由 {@code grid-other/tool-tip-when-truncated}）。 */
@FineUIPage("grid-other/tool-tip-when-truncated")
public class ToolTipWhenTruncated extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }
}
