package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 分页工具栏（重新绑定数据，路由 {@code grid-paging/page-items}）：分页栏内放自定义按钮——
 * 清空数据 / 重新绑定数据 / 选中所有行 / 清空选中行。
 */
@FineUIPage("grid-paging/page-items")
public class PageItems extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }

    public void btnClearData_Click(Object sender, EventArgs e) {
        // 清空数据：数据源置空后重绑，表格显示空占位行
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

    public void btnClearSelect_Click(Object sender, EventArgs e) {
        Grid1.deselectAllRows();
    }
}
