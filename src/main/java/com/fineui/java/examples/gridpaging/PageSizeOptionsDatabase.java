package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 页大小选择器（数据库分页，路由 {@code grid-paging/page-size-options-database}）：内置页大小选择器 + 自定义选项，
 * 数据库分页下改页大小经服务端 {@code onPageSizeChanged} 事件重新绑定（新页大小/页码经回带自动就位）。
 */
@FineUIPage("grid-paging/page-size-options-database")
public class PageSizeOptionsDatabase extends PageBase {

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

    public void Grid1_PageSizeChanged(Object sender, EventArgs e) {
        // 页大小改变：新的 pageSize 与重置为 0 的 pageIndex 已随回带就位，直接重新绑定
        loadData();
    }
}
