package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 右键菜单（路由 {@code grid-tree/context-menu}）：在行上点右键弹出自定义菜单（展开全部/折叠全部）。
 * 菜单弹出时按当前行是否叶子节点决定菜单项禁用状态。全程客户端交互，服务端只绑定数据。
 */
@FineUIPage("grid-tree/context-menu")
public class ContextMenu extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.all());
            Grid1.dataBind();
        }
    }
}
