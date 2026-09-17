package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 排序提示（路由 {@code grid/sorting-tool-tip}）：开启 sorting-tool-tip 后，鼠标悬停可排序列头显示排序提示；服务端排序。
 */
@FineUIPage("grid/sorting-tool-tip")
public class SortingToolTip extends PageBase {

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
