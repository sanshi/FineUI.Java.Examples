package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.examples.code.LargeGridData;
import com.fineui.java.examples.code.PageBase;

/**
 * 序号列（靠右显示，路由 {@code grid/row-number-align}）：数据库分页，序号列右对齐。
 */
@FineUIPage("grid/row-number-align")
public class RowNumberAlign extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setRecordCount(LargeGridData.count());
        Grid1.setDataSource(LargeGridData.paged(Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }
}
