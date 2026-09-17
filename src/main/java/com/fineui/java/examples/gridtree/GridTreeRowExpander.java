package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 行扩展列共存：树表格与行扩展列同时存在，行扩展列用自定义渲染函数输出名称/类型/日期详情。数据服务端绑定。
 */
@FineUIPage("grid-tree/grid-tree-row-expander")
public class GridTreeRowExpander extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.all());
            Grid1.dataBind();
        }
    }
}
