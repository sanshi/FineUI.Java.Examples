package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridRowEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 行取消选中事件（路由 {@code grid/row-select}）。 */
@FineUIPage("grid/row-select")
public class RowSelect extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Grid1_RowSelect(Object sender, GridRowEventArgs e) {
        Object[] keys = Grid1.getDataKeys().get(e.getRowIndex());

        String message = String.format("你选中了第 %d 行，行ID：%s，姓名：%s", e.getRowIndex() + 1, keys[0], keys[1]);

        // 选中单元格所在的列
        String[] selectedCell = Grid1.getSelectedCell();
        if (selectedCell != null && selectedCell.length > 1) {
            message += String.format("，列：%s", selectedCell[1]);
        }

        showNotify(message);
    }

    public void Grid1_RowDeselect(Object sender, GridRowEventArgs e) {
        Object[] keys = Grid1.getDataKeys().get(e.getRowIndex());

        String message = String.format("你取消选中了第 %d 行，行ID：%s，姓名：%s", e.getRowIndex() + 1, keys[0], keys[1]);

        // 选中单元格所在的列
        String[] selectedCell = Grid1.getSelectedCell();
        if (selectedCell != null && selectedCell.length > 1) {
            message += String.format("，列：%s", selectedCell[1]);
        }

        showNotify(message);
    }
}
