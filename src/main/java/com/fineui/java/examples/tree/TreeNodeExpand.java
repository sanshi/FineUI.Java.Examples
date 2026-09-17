package com.fineui.java.examples.tree;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.TreeNodeEventArgs;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.examples.code.PageBase;

/**
 * 节点展开/折叠（回发）（路由 {@code tree/tree-node-expand}）：展开或折叠节点时触发后台回发，把被操作的节点信息回填到 Label。
 */
@FineUIPage("tree/tree-node-expand")
public class TreeNodeExpand extends PageBase {

    Tree Tree1;
    Label labResult;

    public void Tree1_NodeExpand(Object sender, TreeNodeEventArgs e) {
        labResult.setText(String.format("展开节点：%s（%s）", e.getNodeID(), e.getNode().getText()));
    }

    public void Tree1_NodeCollapse(Object sender, TreeNodeEventArgs e) {
        labResult.setText(String.format("折叠节点：%s（%s）", e.getNodeID(), e.getNode().getText()));
    }
}
