package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 排序（取消排序，路由 {@code grid/sorting-cancel}）：列头菜单提供「取消排序」，取消后清除排序字段、恢复原始顺序。
 */
@FineUIPage("grid/sorting-cancel")
public class SortingCancel extends PageBase {

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
