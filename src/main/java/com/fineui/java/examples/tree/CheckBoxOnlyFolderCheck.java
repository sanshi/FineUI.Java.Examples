package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 复选框（只显示目录节点复选框）（路由 {@code tree/check-box-only-folder-check}）：树级 only-folder-check，
 * 仅目录（非叶子）节点显示复选框，服务端获取当前勾选的复选框节点列表。
 */
@FineUIPage("tree/check-box-only-folder-check")
public class CheckBoxOnlyFolderCheck extends PageBase {

    com.fineui.java.core.controls.Tree Tree1;
    Label labResult;

    public void btnGetCheckedValues_Click(Object sender, EventArgs e) {
        List<TreeNode> nodes = Tree1.getCheckedNodes();
        if (!nodes.isEmpty()) {
            StringBuilder sb = new StringBuilder("复选框选中的节点：<ul>");
            for (TreeNode node : nodes) {
                sb.append(String.format("<li>%s（%s）</li>", node.getId(), node.getText()));
            }
            sb.append("</ul>");
            labResult.setTextRawHtml(new RawHtml(sb.toString()));
        } else {
            labResult.setText("没有复选框选中的节点");
        }
    }
}
