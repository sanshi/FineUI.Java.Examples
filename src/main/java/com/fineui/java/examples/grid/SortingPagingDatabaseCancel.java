package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 数据库分页排序（可取消，路由 {@code grid/sorting-paging-database-cancel}）：服务端分页 + 服务端排序，
 * 列头菜单提供「取消排序」；取消后回到默认排序。
 */
@FineUIPage("grid/sorting-paging-database-cancel")
public class SortingPagingDatabaseCancel extends PageBase {

    Grid Grid1;

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
