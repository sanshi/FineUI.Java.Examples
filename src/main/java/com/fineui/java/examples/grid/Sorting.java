package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.examples.code.GridSelectionMessage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 排序（路由 {@code grid/sorting}）：服务端排序（{@code onSort}）。初始按性别升序，点击列头按该列服务端重新排序取数。
 */
@FineUIPage("grid/sorting")
public class Sorting extends PageBase {

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

    public void Button1_Click(Object sender, EventArgs e) {
        showNotifyRaw(GridSelectionMessage.howManyRowsAreSelected(Grid1));
    }
}
