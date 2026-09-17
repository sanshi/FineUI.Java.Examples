package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.TreeNodeEventArgs;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉树（多选，延迟加载）演示页（路由 {@code drop-down-box/tree-multi-select-lazy-load}）：弹出树的部分节点
 * 首屏不加载子节点，展开时向服务端请求、动态生成子节点并下发追加；「获取下拉框的选中值」回发读取当前文本与全部选中值。
 */
@FineUIPage("drop-down-box/tree-multi-select-lazy-load")
public class TreeMultiSelectLazyLoad extends PageBase {

    DropDownBox DropDownBox1;
    Tree Tree1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownBox1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", text, String.join(", ", DropDownBox1.getValues())));
        } else {
            labResult.setText("下拉框为空");
        }
    }

    public void Tree1_NodeLazyLoad(Object sender, TreeNodeEventArgs e) {
        Tree1.loadData(e.getNodeID(), dynamicAppendNode(e.getNodeID()));
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
