package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 大数据：约 3500 个节点，用于测试展开/折叠节点时的性能；行号列开启树形行号、固定宽度 80。数据服务端绑定。
 */
@FineUIPage("grid-tree/grid-tree-large-data")
public class GridTreeLargeData extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.largeTree());
            Grid1.dataBind();
        }
    }
}
