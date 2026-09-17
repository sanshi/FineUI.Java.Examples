package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 分页工具栏（改变每页记录数，数据库分页，路由 {@code grid-paging/page-items-page-size-database}）：改每页记录数经
 * 服务端回发（下拉列表 {@code onSelectedIndexChanged}）设置 {@code pageSize} 后重新绑定当前页数据。
 */
@FineUIPage("grid-paging/page-items-page-size-database")
public class PageItemsPageSizeDatabase extends PageBase {

    Grid Grid1;
    DropDownList ddlPageSize;

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

    public void ddlPageSize_SelectedIndexChanged(Object sender, EventArgs e) {
        Grid1.setPageSize(Integer.parseInt(ddlPageSize.getSelectedValue()));
        loadData();
    }
}
