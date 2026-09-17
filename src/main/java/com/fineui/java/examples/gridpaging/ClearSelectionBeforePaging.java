package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 跨页保持选中行（路由 {@code grid-paging/clear-selection-before-paging}）：客户端分页，配合
 * {@code clear-selection-before-paging=false} + {@code keep-current-selection} + {@code keep-paged-selection}，
 * 翻页后已选中行不丢失。
 */
@FineUIPage("grid-paging/clear-selection-before-paging")
public class ClearSelectionBeforePaging extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }
}
