package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.GridSort;
import java.util.ArrayList;

/** 回发开启数据库排序；排序事件读取当前条件，查询并绑定结果。示例使用内存数据模拟数据库。 */
@FineUIPage("grid-dynamic/sorting-database")
public class SortingDatabase extends PageBase {
    Grid Grid1;
    Label LabelResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        var rows = new ArrayList<>(StudentGridData.rows());
        if (Grid1.isSortingMulti()) {
            GridSort.sortInPlaceMulti(rows, Grid1.getSortFieldArray());
        } else {
            GridSort.sortInPlace(rows, Grid1.getSortField(), Grid1.getSortDirection());
        }
        Grid1.setDataSource(rows);
        Grid1.dataBind();
    }

    public void Button1_Click(Object sender, EventArgs e) {
        Grid1.setAllowSorting(true);
        Grid1.setSortingMulti(false);
        Grid1.setSortField("EntranceYear");
        Grid1.setSortDirection("DESC");
        loadData();
    }

    public void Button2_Click(Object sender, EventArgs e) {
        Grid1.setAllowSorting(false);
        Grid1.setSortingMulti(false);
    }

    public void Button3_Click(Object sender, EventArgs e) {
        LabelResult.setText("当前列数：" + Grid1.getColumns().size() + "；分页：" + Grid1.isAllowPaging()
                + "；页大小：" + Grid1.getPageSize() + "；排序：" + Grid1.isAllowSorting()
                + "；多选：" + Grid1.isEnableMultiSelect());
    }

    public void BusinessButton_Click(Object sender, EventArgs e) {
        LabelResult.setText("业务按钮仍可回发。");
    }

    public void Button4_Click(Object sender, EventArgs e) {
        Grid1.setAllowSorting(true);
        Grid1.setSortingMulti(true);
        Grid1.setSortFieldArray(new String[] { "EntranceYear", "DESC", "Name", "ASC" });
        loadData();
    }
    public void Grid1_Sort(Object sender, EventArgs e) {
        loadData();
        LabelResult.setText("数据库排序事件已执行。");
    }
}
