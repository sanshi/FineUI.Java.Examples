package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.GridSort;

/**
 * 多列排序（路由 {@code grid/sorting-multi}）：服务端排序。按住 Shift 点击多个列头可叠加排序列，
 * 服务端按多列排序数组重新取数并更新排序提示。初始按「入学年份降序，性别升序」。
 */
@FineUIPage("grid/sorting-multi")
public class SortingMulti extends PageBase {

    Grid Grid1;
    Label labSortOrderTip;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.sortedRowsMulti(Grid1.getSortFieldArray()));
        Grid1.dataBind();

        labSortOrderTip.setText(GridSort.buildSortTip(Grid1, Grid1.getSortFieldArray()));
    }

    public void Grid1_Sort(Object sender, GridSortEventArgs e) {
        loadData();
    }
}
