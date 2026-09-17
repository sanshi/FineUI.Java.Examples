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
 * 服务端改变多列排序（路由 {@code grid/sorting-multi-server}）：除列头 Shift 叠加排序外，
 * 点击按钮由服务端直接设置多列排序数组（[入学年份升序，姓名降序]），客户端列头排序序号随之同步。
 */
@FineUIPage("grid/sorting-multi-server")
public class SortingMultiServer extends PageBase {

    Grid Grid1;
    Label labSortOrderTip;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        String[] sortFields = Grid1.getSortFieldArray();
        if (sortFields != null && sortFields.length > 0) {
            Grid1.setDataSource(StudentGridData.sortedRowsMulti(sortFields));
        } else {
            Grid1.setDataSource(StudentGridData.rows());
        }
        Grid1.dataBind();

        labSortOrderTip.setText(GridSort.buildSortTip(Grid1, Grid1.getSortFieldArray()));
    }

    public void Grid1_Sort(Object sender, GridSortEventArgs e) {
        loadData();
    }

    public void btnChangeOnServer_Click(Object sender, EventArgs e) {
        // 服务端设置排序字段和方向
        Grid1.setSortFieldArray(new String[]{"EntranceYear", "ASC", "Name", "DESC"});

        // 更新表格数据源
        loadData();
    }
}
