package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 分页工具栏（左中右，路由 {@code grid-paging/page-items-toolbar-fill}）：用 {@code <f:toolbar-fill>} 弹性填充
 * 把分页栏按钮撑成左中右布局。
 */
@FineUIPage("grid-paging/page-items-toolbar-fill")
public class PageItemsToolbarFill extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }

    public void btnClearData_Click(Object sender, EventArgs e) {
        Grid1.setDataSource(null);
        Grid1.dataBind();
    }

    public void btnRebindData_Click(Object sender, EventArgs e) {
        Grid1.setDataSource(StudentGridData2.rows());
        Grid1.dataBind();
    }

    public void btnSelectAll_Click(Object sender, EventArgs e) {
        Grid1.selectAllRows();
    }
}
