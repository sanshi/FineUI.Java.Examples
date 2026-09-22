package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 分页工具栏（改变每页记录数，内存分页，路由 {@code grid-paging/page-items-page-size}）：分页栏内放下拉列表，
 * 改每页记录数由客户端即时生效（setPageSize + loadPageData，无服务端回发）。
 */
@FineUIPage("grid-paging/page-items-page-size")
public class PageItemsPageSize extends PageBase {

    Grid Grid1;

    public void btnServerPage_Click(Object sender, EventArgs e) {
        Grid1.loadPageData(1);
    }

    public void btnServerSort_Click(Object sender, EventArgs e) {
        Grid1.loadSortData("EntranceYear", "DESC");
        Grid1.loadPageData(0);
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }
}
