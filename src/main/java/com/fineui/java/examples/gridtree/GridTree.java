package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 最基础示例（路由 {@code grid-tree/grid-tree}）：按父子字段（Id/ParentId）构建层级，
 * Name 列渲染为树列（展开/缩进/连线）。数据服务端绑定。
 */
@FineUIPage("grid-tree/grid-tree")
public class GridTree extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.all());
            Grid1.dataBind();
        }
    }
}
