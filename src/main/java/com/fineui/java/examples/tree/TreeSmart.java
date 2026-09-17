package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 智能树（路由 {@code tree/tree-smart}）：综合演示（无头样式、单击展开、初始选中、多国家根节点、字体图标）；
 * 首屏为每个节点设置 Tooltip；客户端复选框切换水平/垂直滚动条、折叠图标位置、标题栏样式、微型模式等外观属性。
 */
@FineUIPage("tree/tree-smart")
public class TreeSmart extends PageBase {

    Tree Tree1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            resolveTreeNodes(Tree1.getNodes());
        }
    }

    /** 为每个节点设置 Tooltip（值取节点文本）。 */
    private void resolveTreeNodes(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            node.setToolTip(node.getText());
            if (!node.getChildren().isEmpty()) {
                resolveTreeNodes(node.getChildren());
            }
        }
    }
}
