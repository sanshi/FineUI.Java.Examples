package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 序号列（数据库分页，路由 {@code grid/row-number-paging-database}）：序号列 {@code enable-paging-number}，
 * 切换分页（服务端回发）时序号连续。
 */
@FineUIPage("grid/row-number-paging-database")
public class RowNumberPagingDatabase extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setRecordCount(StudentGridData2.count());
        Grid1.setDataSource(StudentGridData2.paged(Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }
}
