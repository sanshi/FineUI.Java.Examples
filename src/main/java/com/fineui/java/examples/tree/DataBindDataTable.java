package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 绑定关系型数据（路由 {@code tree/data-bind-data-table}）：从"自引用关系"的扁平数据（每行含 Id/Text/ParentId）
 * 按父子关系自动构建树层级，目录节点默认展开。
 */
@FineUIPage("tree/data-bind-data-table")
public class DataBindDataTable extends PageBase {

    Tree Tree1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    /** 每行：{Id, Text, ParentId}（ParentId 为 null 表示根）。 */
    private static final String[][] ROWS = {
            {"china", "中国", null},
            {"henan", "河南省", "china"},
            {"zhumadian", "驻马店市", "henan"},
            {"luohe", "漯河市", "henan"},
            {"anhui", "安徽省", "china"},
            {"hefei", "合肥市", "anhui"},
            {"golden", "金色池塘小区", "hefei"},
            {"ustc", "中国科学技术大学", "hefei"},
    };

    private void loadData() {
        // 建立 id → 节点 与 父 id → 子行 索引，再从根行递归建树
        Map<String, TreeNode> byId = new LinkedHashMap<>();
        Map<String, List<String[]>> childrenOf = new LinkedHashMap<>();
        for (String[] row : ROWS) {
            TreeNode node = new TreeNode();
            node.setText(row[1]);
            byId.put(row[0], node);
            childrenOf.computeIfAbsent(row[2], k -> new ArrayList<>()).add(row);
        }
        for (String[] row : ROWS) {
            if (row[2] == null) {
                Tree1.addNode(byId.get(row[0]));
            }
            resolveSubTree(row[0], byId, childrenOf);
        }
    }

    private void resolveSubTree(String id, Map<String, TreeNode> byId, Map<String, List<String[]>> childrenOf) {
        List<String[]> children = childrenOf.get(id);
        if (children == null || children.isEmpty()) {
            return;
        }
        TreeNode parent = byId.get(id);
        parent.setExpanded(true);   // 有子节点的目录默认展开
        for (String[] childRow : children) {
            parent.addChild(byId.get(childRow[0]));
        }
    }
}
