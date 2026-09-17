package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.TreeNodeEventArgs;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 延迟加载（路由 {@code tree/tree-lazy-load}）：展开尚未加载子节点的文件夹时向服务端请求，服务端动态生成子节点并下发追加。
 */
@FineUIPage("tree/tree-lazy-load")
public class TreeLazyLoad extends PageBase {

    Tree Tree1;
    Label labResult;

    public void Tree1_NodeLazyLoad(Object sender, TreeNodeEventArgs e) {
        Tree1.loadData(e.getNodeID(), dynamicAppendNode(e.getNodeID()));
    }

    public void btnGetSelectedNode_Click(Object sender, EventArgs e) {
        String selectedId = Tree1.getSelectedNodeId();
        if (!selectedId.isEmpty()) {
            labResult.setText(String.format("选中的节点：%s（%s）", Tree1.findNode(selectedId).getText(), selectedId));
        } else {
            labResult.setText("没有选中节点");
        }
    }

    static List<TreeNode> dynamicAppendNode(String nodeId) {
        List<TreeNode> nodes = new ArrayList<>();
        switch (nodeId) {
            case "zhumadian" -> {
                nodes.add(node("遂平县（延迟加载）", "suiping", false));
                nodes.add(node("西平县", "xiping", true));
            }
            case "suiping" -> {
                nodes.add(node("槐树乡（延迟加载）", "huaishu", false));
                nodes.add(node("嵖岈山乡", "chayashan", true));
            }
            case "huaishu" -> {
                nodes.add(node("陈庄村", "chenzhuang", true));
                nodes.add(node("王老庄", "wanglaozhuang", true));
            }
            default -> { /* 其它节点无延迟加载子节点 */ }
        }
        return nodes;
    }

    private static TreeNode node(String text, String id, boolean leaf) {
        TreeNode n = new TreeNode();
        n.setText(text);
        n.setId(id);
        n.setLeaf(leaf);
        return n;
    }
}
