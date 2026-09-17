package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 内存分页与排序（路由 {@code grid/sorting-paging}）：服务端排序 + 内存分页——全量数据一次性排序后绑定，
 * 客户端分页；排序经服务端回发重新排序全量。
 */
@FineUIPage("grid/sorting-paging")
public class SortingPaging extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.sortedRows(Grid1.getSortField(), Grid1.getSortDirection()));
        Grid1.dataBind();
    }

    public void Grid1_Sort(Object sender, GridSortEventArgs e) {
        loadData();
    }
}
