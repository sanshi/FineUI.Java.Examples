package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridCommandEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 行命令（多个图标命令，文本居中显示）（路由 {@code grid/row-command-multi-commands-text-align}）。 */
@FineUIPage("grid/row-command-multi-commands-text-align")
public class RowCommandMultiCommandsTextAlign extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
    public void Grid1_RowCommand(Object sender, GridCommandEventArgs e) {
        java.util.List<Object[]> dataKeys = Grid1.getDataKeys();
        Object[] keys = e.getRowIndex() >= 0 && e.getRowIndex() < dataKeys.size() ? dataKeys.get(e.getRowIndex()) : new Object[0];
        showNotify(String.format("你点击了第 %d 行，第 %d 列，行命令：%s，行ID：%s，姓名：%s",
                e.getRowIndex() + 1, e.getColumnIndex() + 1, e.getCommandName(),
                keys.length > 0 ? keys[0] : "", keys.length > 1 ? keys[1] : ""));
    }
}
