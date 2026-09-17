package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridRowEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 网址数据源 · 行双击回发（路由 {@code grid-data-url/row-double-click}）：数据由 {@code data-url} 客户端拉取，
 * {@code data-key-names} 指定每行随回发带回的数据键（Id、Name）。双击行触发服务端事件，读取该行数据键弹通知。
 */
@FineUIPage("grid-data-url/row-double-click")
public class RowDoubleClick extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        // 数据由客户端向 data-url 拉取，服务端不绑定。
    }

    public void Grid1_RowDblClick(Object sender, GridRowEventArgs e) {
        List<Object[]> dataKeys = Grid1.getDataKeys();
        int rowIndex = e.getRowIndex();
        Object rowId = "";
        Object rowName = "";
        if (rowIndex >= 0 && rowIndex < dataKeys.size()) {
            Object[] keys = dataKeys.get(rowIndex);
            if (keys.length > 0) {
                rowId = keys[0];
            }
            if (keys.length > 1) {
                rowName = keys[1];
            }
        }
        showNotify(String.format("你双击了第 %d 行，行ID：%s，姓名：%s", rowIndex + 1, rowId, rowName));
    }
}
