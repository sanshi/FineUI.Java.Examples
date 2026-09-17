package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 跨页保持选中行 + 客户端缓存选中明细（路由 {@code grid-paging/clear-selection-before-paging-details}）：
 * 翻页时 {@code dataload}/{@code selectionchange} 客户端缓存当前页选中行数据，点「选中了哪些行」弹出明细表。
 */
@FineUIPage("grid-paging/clear-selection-before-paging-details")
public class ClearSelectionBeforePagingDetails extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }
}
