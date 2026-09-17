package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 字体图标颜色（路由 {@code tree/tree-icon-font-color}）：节点字体图标 + CssClass 着色；服务端读取当前选中节点。
 */
@FineUIPage("tree/tree-icon-font-color")
public class TreeIconFontColor extends PageBase {

    com.fineui.java.core.controls.Tree Tree1;
    Label labResult;

    public void btnGetSelectedNode_Click(Object sender, EventArgs e) {
        String selectedId = Tree1.getSelectedNodeId();
        if (!selectedId.isEmpty()) {
            labResult.setText(String.format("选中的节点：%s（%s）", Tree1.getSelectedNode().getText(), selectedId));
        } else {
            labResult.setText("没有选中节点");
        }
    }
}
