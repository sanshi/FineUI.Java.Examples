package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 数据库分页与排序（路由 {@code grid/sorting-paging-database}）：翻页与排序都经服务端回发，
 * 服务端同时按当前页 + 当前排序字段取数。
 */
@FineUIPage("grid/sorting-paging-database")
public class SortingPagingDatabase extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setRecordCount(StudentGridData2.count());
        Grid1.setDataSource(StudentGridData2.pagedSorted(
                Grid1.getPageIndex(), Grid1.getPageSize(), Grid1.getSortField(), Grid1.getSortDirection()));
        Grid1.dataBind();
    }

    public void Grid1_Sort(Object sender, EventArgs e) {
        loadData();
    }

    public void Grid1_PageIndexChanged(Object sender, EventArgs e) {
        loadData();
    }
}
