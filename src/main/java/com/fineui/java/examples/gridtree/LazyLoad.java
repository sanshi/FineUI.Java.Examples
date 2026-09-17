package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridRowEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 服务端延迟加载（路由 {@code grid-tree/lazy-load}）：首屏只绑定根节点，
 * 文件夹节点客户端标记为非叶子（{@code row-data-bound-function}）；展开时经服务端回发
 * （{@code on-row-lazy-load}），按行 id 取该文件夹的子节点并追加到表格。
 */
@FineUIPage("grid-tree/lazy-load")
public class LazyLoad extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 首屏只绑定根节点，子节点等展开时再延迟加载
        Grid1.setDataSource(FileTreeData.lazyRoots());
        Grid1.dataBind();
    }

    public void Grid1_RowLazyLoad(Object sender, GridRowEventArgs e) {
        // 按被展开行的 id 取其子节点并追加（50=basic、54=Captcha，其余无子节点）
        String rowId = e.getRowID();
        Grid1.loadData(rowId, FileTreeData.lazyChildrenOf(Integer.parseInt(rowId)));
    }
}
