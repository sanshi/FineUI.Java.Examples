package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 锁定列 + 大数据：约 3500 个节点，开启 allow-column-locking 并锁定树列，行号列关闭树形行号、固定宽度 80。数据服务端绑定。
 */
@FineUIPage("grid-tree/lock-column-large-data")
public class LockColumnLargeData extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.largeTree());
            Grid1.dataBind();
        }
    }
}
