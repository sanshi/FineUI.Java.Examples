package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 后台代码更新分页索引（路由 {@code grid-paging/database-update-page-index}）：数据库分页表格，
 * 「转到最后一页」按钮在服务端直接设置 {@code pageIndex} 到末页并重新绑定数据。
 */
@FineUIPage("grid-paging/database-update-page-index")
public class DatabaseUpdatePageIndex extends PageBase {

    Grid Grid1;

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

    public void btnUpdatePageIndex_Click(Object sender, EventArgs e) {
        int recordCount = StudentGridData2.count();
        // 1. 设置总项数
        Grid1.setRecordCount(recordCount);
        // 2. 设置当前分页索引（最后一页）
        int lastPageIndex = (int) Math.ceil((double) recordCount / Grid1.getPageSize()) - 1;
        Grid1.setPageIndex(lastPageIndex);
        // 3. 获取当前分页数据
        Grid1.setDataSource(StudentGridData2.paged(lastPageIndex, Grid1.getPageSize()));
        Grid1.dataBind();
    }
}
