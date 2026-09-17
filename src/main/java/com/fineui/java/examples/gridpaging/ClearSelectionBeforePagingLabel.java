package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 跨页保持选中行 + 标签实时汇总（路由 {@code grid-paging/clear-selection-before-paging-label}）：
 * {@code dataload}/{@code selectionchange} 客户端缓存并实时更新标签，展示当前跨页选中的全部行。
 */
@FineUIPage("grid-paging/clear-selection-before-paging-label")
public class ClearSelectionBeforePagingLabel extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }
}
