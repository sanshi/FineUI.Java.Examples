package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.LargeGridData;
import com.fineui.java.examples.code.PageBase;

/**
 * 窄屏自动简洁分页（路由 {@code grid-paging/pager-auto-simple-mode}）：在 PageManager 级别开启
 * {@code gridPagerAutoSimpleMode}，当分页栏放不下时自动降级为简洁形态（上一页 + 当前/总页 + 下一页）。
 */
@FineUIPage("grid-paging/pager-auto-simple-mode")
public class PagerAutoSimpleMode extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            getPageManager().gridPagerAutoSimpleMode(true);
            loadData();
        }
    }

    private void loadData() {
        Grid1.setRecordCount(LargeGridData.count());
        Grid1.setDataSource(LargeGridData.paged(Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }
}
