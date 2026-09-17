package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 快速排序列（路由 {@code grid/sorting-quick-sort-field}）：用 quick-sort-field 让列直接可排序（无需显式 sort-field），
 * 服务端排序。
 */
@FineUIPage("grid/sorting-quick-sort-field")
public class SortingQuickSortField extends PageBase {

    Grid Grid1;

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
