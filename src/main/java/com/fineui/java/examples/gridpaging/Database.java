package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 数据库分页（路由 {@code grid-paging/database}）：每次翻页经服务端回发，按新页码只取当前页数据。
 * 关键：初始化与翻页都要先设总记录数 {@code recordCount}，客户端才能算出总页数。
 */
@FineUIPage("grid-paging/database")
public class Database extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 1. 设置总记录数（数据库分页初始化时必设，客户端据此算总页数）
        Grid1.setRecordCount(StudentGridData2.count());
        // 2. 只取当前页数据
        Grid1.setDataSource(StudentGridData2.paged(Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }
}
