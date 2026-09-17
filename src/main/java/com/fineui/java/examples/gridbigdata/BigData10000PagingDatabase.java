package com.fineui.java.examples.gridbigdata;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

/** 大数据虚拟滚动（10,000 行，服务端数据库分页）。 */
@FineUIPage("grid-big-data/big-data10000-paging-database")
public class BigData10000PagingDatabase extends PageBase {
    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) { loadData(); }
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }

    private void loadData() {
        Grid1.setRecordCount(10_000);
        Grid1.setDataSource(BigDataData.paged(10_000, Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }
}
