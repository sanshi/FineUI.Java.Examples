package com.fineui.java.examples.gridrowgroup;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 行分组（数据库分页与排序，分组合计 + 当前页合计，路由 {@code grid-row-group/database-sorting-summary-current-page}）：
 * 底部合计行按当前页数据在客户端计算（列的 {@code SummaryType=Avg}），服务端只负责分页/排序数据。
 */
@FineUIPage("grid-row-group/database-sorting-summary-current-page")
public class DatabaseSortingSummaryCurrentPage extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setRecordCount(StudentGridData2.count());
        Grid1.setDataSource(StudentGridData2.pagedSorted(Grid1.getPageIndex(), Grid1.getPageSize(),
                Grid1.getSortField(), Grid1.getSortDirection()));
        Grid1.dataBind();
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }

    public void Grid1_Sort(Object sender, GridSortEventArgs e) {
        loadData();
    }
}
