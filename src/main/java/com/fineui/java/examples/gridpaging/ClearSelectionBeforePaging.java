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
    com.fineui.java.core.controls.Label labServerSelection;

    public void btnSelectCell_Click(Object sender, EventArgs e) {
        // 106 行在第二页；有序命令选择的是当前客户端渲染页中的单元格。
        Grid1.selectCell("106", "Name");
    }

    public void btnClearSelections_Click(Object sender, EventArgs e) {
        Grid1.clearSelections();
    }

    public void btnReadSelection_Click(Object sender, EventArgs e) {
        String[] cell = Grid1.getSelectedCell();
        String cellText = cell == null || cell.length == 0 ? "空" : String.join(",", cell);
        labServerSelection.setText("选中行数：" + Grid1.getSelectedRowIdArray().length + "；单元格：" + cellText);
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }
}
