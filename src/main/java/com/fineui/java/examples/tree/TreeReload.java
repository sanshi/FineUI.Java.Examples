package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 重新加载节点（路由 {@code tree/tree-reload}）：服务端重建某个节点的子树并下发，客户端就地更新该子树；
 * 同时演示服务端读取当前选中的节点。
 */
@FineUIPage("tree/tree-reload")
public class TreeReload extends PageBase {

    Tree Tree1;
    HiddenField hfDataSource;
    Label labResult;

    public void btnGetSelectedNode_Click(Object sender, EventArgs e) {
        String selectedId = Tree1.getSelectedNodeId();
        if (!selectedId.isEmpty()) {
            labResult.setText(String.format("选中的节点：%s（%s）", Tree1.findNode(selectedId).getText(), selectedId));
        } else {
            labResult.setText("没有选中节点");
        }
    }

    public void btnUpdateNode_Click(Object sender, EventArgs e) {
        List<TreeNode> source = "source1".equals(hfDataSource.getText()) ? getSource2() : getSource1();
        hfDataSource.setText("source1".equals(hfDataSource.getText()) ? "source2" : "source1");

        // 重建 zhumadian 的子树并展开更新后的节点
        Tree1.loadData("zhumadian", source);
        Tree1.expandNode("zhumadian");
    }

    private List<TreeNode> getSource2() {
        List<TreeNode> nodes = new ArrayList<>();

        TreeNode suiping = node("遂平县", "suiping", false);
        nodes.add(suiping);

        TreeNode huaishu = node("槐树乡", "huaishu", false);
        suiping.addChild(huaishu);
        huaishu.addChild(node("陈庄村", "chenzhuang", true));
        huaishu.addChild(node("王老庄", "wanglaozhuang", true));
        suiping.addChild(node("嵖岈山乡", "chayashan", true));

        nodes.add(node("西平县", "xiping", true));
        return nodes;
    }

    private List<TreeNode> getSource1() {
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(node("平舆县", "pingyu", true));
        nodes.add(node("汝南县", "runan", true));
        nodes.add(node("新蔡县", "xincai", true));
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
