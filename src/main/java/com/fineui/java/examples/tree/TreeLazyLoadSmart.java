package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.TreeNodeEventArgs;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.examples.code.PageBase;

/**
 * 延迟加载 + 微型模式（路由 {@code tree/tree-lazy-load-smart}）：延迟加载动态生成子节点；
 * 复选框切换客户端微型模式（客户端脚本改 {@code tree.miniMode} 并 {@code loadData} 重载）。
 */
@FineUIPage("tree/tree-lazy-load-smart")
public class TreeLazyLoadSmart extends PageBase {

    Tree Tree1;
    Label labResult;

    public void Tree1_NodeLazyLoad(Object sender, TreeNodeEventArgs e) {
        Tree1.loadData(e.getNodeID(), TreeLazyLoad.dynamicAppendNode(e.getNodeID()));
    }

    public void btnGetSelectedNode_Click(Object sender, EventArgs e) {
        String selectedId = Tree1.getSelectedNodeId();
        if (!selectedId.isEmpty()) {
            labResult.setText(String.format("选中的节点：%s（%s）", Tree1.findNode(selectedId).getText(), selectedId));
        } else {
            labResult.setText("没有选中节点");
        }
    }
}
