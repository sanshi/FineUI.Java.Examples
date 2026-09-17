package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridRowEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

import java.util.List;

/**
 * 树表格 · 双击展开改为双击回发（路由 {@code grid-tree/expand-on-dbl-click}）：关闭双击展开树节点
 * （{@code tree-expand-on-dbl-click=false}），改为双击行触发服务端事件；{@code data-key-names} 指定
 * 随回发带回的数据键（Id、Name），处理器按行序号读取并弹通知。
 */
@FineUIPage("grid-tree/expand-on-dbl-click")
public class ExpandOnDblClick extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.all());
            Grid1.dataBind();
        }
    }

    public void Grid1_RowDoubleClick(Object sender, GridRowEventArgs e) {
        int rowIndex = e.getRowIndex();
        List<Object[]> dataKeys = Grid1.getDataKeys();
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
        showNotify(String.format("你双击了第 %d 行，行ID：%s，名称：%s", rowIndex + 1, rowId, rowName));
    }
}
