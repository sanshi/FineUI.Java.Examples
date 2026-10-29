package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/** 动态修改分页（数据库数据）：示例用数据工具模拟数据库查询。开启分页后只读取当前页，并设置总记录数；关闭分页时重新读取全部数据，不能把已加载的一页当成完整数据。 */
@FineUIPage("grid-dynamic/paging-database")
public class PagingDatabase extends PageBase {
    Grid Grid1;
    Label LabelResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setRecordCount(StudentGridData2.count());
        Grid1.setDataSource(Grid1.isAllowPaging()
                ? StudentGridData2.paged(Grid1.getPageIndex(), Grid1.getPageSize())
                : StudentGridData2.rows());
        Grid1.dataBind();
    }

    public void Button1_Click(Object sender, EventArgs e) {
        Grid1.setAllowPaging(true);
        Grid1.setPageIndex(0);
        Grid1.setPageSize(5);
        Grid1.setShowPageSizeSelector(true);
        Grid1.setPageSizeOptions(3, 5, 10);
        loadData();
    }

    public void Button2_Click(Object sender, EventArgs e) {
        Grid1.setAllowPaging(false);
        loadData();
    }

    public void Button3_Click(Object sender, EventArgs e) {
        LabelResult.setText("当前列数：" + Grid1.getColumns().size() + "；分页：" + Grid1.isAllowPaging()
                + "；页大小：" + Grid1.getPageSize() + "；排序：" + Grid1.isAllowSorting()
                + "；多选：" + Grid1.isEnableMultiSelect());
    }

    public void BusinessButton_Click(Object sender, EventArgs e) {
        LabelResult.setText("业务按钮仍可回发。");
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }

    public void Grid1_PageSizeChanged(Object sender, EventArgs e) {
        loadData();
    }
}
