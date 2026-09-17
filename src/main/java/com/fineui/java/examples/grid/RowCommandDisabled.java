package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridCommandEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridCommand;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行命令（禁用行命令，路由 {@code grid/row-command-disabled}）：Action1 在页面标签中禁用、Action2 在后台代码中禁用。
 */
@FineUIPage("grid/row-command-disabled")
public class RowCommandDisabled extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.findCommand("Action2").setEnabled(false);   // 后台代码禁用名称为 Action2 的命令
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Grid1_RowCommand(Object sender, GridCommandEventArgs e) {
        GridCommand commandInstance = Grid1.findCommand(e.getCommandName());
        java.util.List<Object[]> dataKeys = Grid1.getDataKeys();
        Object[] keys = e.getRowIndex() >= 0 && e.getRowIndex() < dataKeys.size() ? dataKeys.get(e.getRowIndex()) : new Object[0];
        showNotify(String.format("你点击了第 %d 行，第 %d 列，行命令：%s，行ID：%s，姓名：%s，IconFont：%s",
                e.getRowIndex() + 1, e.getColumnIndex() + 1, e.getCommandName(),
                keys.length > 0 ? keys[0] : "", keys.length > 1 ? keys[1] : "",
                commandInstance.getIconFont()));
    }
}
