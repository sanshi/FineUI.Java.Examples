package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 排序（服务器端改变排序列，路由 {@code grid/sorting-server}）：服务端排序（{@code onSort}），初始无排序字段，
 * 点击列头按该列服务端重新取数。
 */
@FineUIPage("grid/sorting-server")
public class SortingServer extends PageBase {

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

    public void Button2_Click(Object sender, EventArgs e) {
        // 服务端改变排序列：设为按入学年份降序，重新绑定并把新排序态下发给客户端（更新列头指示器）。
        Grid1.setSortField("EntranceYear");
        Grid1.setSortDirection("DESC");
        loadData();
    }
}
