package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridRowEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 行单击回发事件（阻止列的行点击事件）（路由 {@code grid/row-click-before-row-click}）。 */
@FineUIPage("grid/row-click-before-row-click")
public class RowClickBeforeRowClick extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Grid1_RowClick(Object sender, GridRowEventArgs e) {
        Object[] keys = Grid1.getDataKeys().get(e.getRowIndex());

        String message = String.format("你单击了第 %d 行，行ID：%s，姓名：%s", e.getRowIndex() + 1, keys[0], keys[1]);

        // 选中单元格所在的列
        String[] selectedCell = Grid1.getSelectedCell();
        if (selectedCell != null && selectedCell.length > 1) {
            message += String.format("，列：%s", selectedCell[1]);
        }

        showNotify(message);
    }
}
