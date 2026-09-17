package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;
import com.fineui.java.examples.code.GridSort;

/**
 * 数据库分页 + 多列排序（路由 {@code grid/sorting-paging-database-multi}）：翻页与 Shift 叠加排序都经服务端回发，
 * 服务端同时按当前页 + 多列排序数组取数并更新排序提示。
 */
@FineUIPage("grid/sorting-paging-database-multi")
public class SortingPagingDatabaseMulti extends PageBase {

    Grid Grid1;
    Label labSortOrderTip;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 1.设置总项数（数据库分页初始化时必须设置总记录数）
        Grid1.setRecordCount(StudentGridData2.count());

        // 2.获取当前分页 + 多列排序的数据
        Grid1.setDataSource(StudentGridData2.pagedSortedMulti(
                Grid1.getPageIndex(), Grid1.getPageSize(), Grid1.getSortFieldArray()));
        Grid1.dataBind();

        labSortOrderTip.setText(GridSort.buildSortTip(Grid1, Grid1.getSortFieldArray()));
    }

    public void Grid1_Sort(Object sender, GridSortEventArgs e) {
        loadData();
    }

    public void Grid1_PageIndexChanged(Object sender, EventArgs e) {
        loadData();
    }
}
