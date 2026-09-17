package com.fineui.java.examples.gridother;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 选中信息自定义渲染（路由 {@code grid-other/grid-selection-message-renderer}）：
 * 数据库分页；分页工具栏的选中信息由自定义渲染函数生成（显示已选行数与学费合计）。
 */
@FineUIPage("grid-other/grid-selection-message-renderer")
public class GridSelectionMessageRenderer extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 1.设置总项数（数据库分页初始化时必须设置总记录数）
        Grid1.setRecordCount(StudentGridData2.count());
        // 2.获取当前分页数据
        Grid1.setDataSource(StudentGridData2.paged(Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }

    public void Grid1_PageIndexChanged(Object sender, EventArgs e) {
        loadData();
    }
}
