package com.fineui.java.examples.grid;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 数据库分页排序（自定义回发，路由 {@code grid/sorting-paging-database-custom}）：不用内置服务端事件，
 * 而是客户端 {@code paging}/{@code sorting} 监听里统一触发一个自定义事件回发取数。
 */
@FineUIPage("grid/sorting-paging-database-custom")
public class SortingPagingDatabaseCustom extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_PageIndexChangedOrSort".equals(e.getEventName())) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setRecordCount(StudentGridData2.count());
        Grid1.setDataSource(StudentGridData2.pagedSorted(Grid1.getPageIndex(), Grid1.getPageSize(),
                Grid1.getSortField(), Grid1.getSortDirection()));
        Grid1.dataBind();
    }
}
