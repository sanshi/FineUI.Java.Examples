package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 分页工具栏（展开折叠行扩展列，路由 {@code grid-paging/page-items-row-expander}）：分页栏放一个按下态按钮，
 * 点击经服务端切换 {@code expandAllRowExpanders} 显示/隐藏全部行描述。
 */
@FineUIPage("grid-paging/page-items-row-expander")
public class PageItemsRowExpander extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }

    public void btnShowRowExpanders_Click(Object sender, EventArgs e) {
        Grid1.setExpandAllRowExpanders(!Grid1.isExpandAllRowExpanders());
    }
}
