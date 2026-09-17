package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点选择（禁止选中节点）（路由 {@code tree/tree-select-multi-selectable}）：多选树，部分目录节点 selectable=false 不可选中，
 * 服务端读取当前多选节点列表，并可继续追加选中节点。
 */
@FineUIPage("tree/tree-select-multi-selectable")
public class TreeSelectMultiSelectable extends PageBase {

    com.fineui.java.core.controls.Tree Tree1;
    Label labResult;

    public void btnGetSelectedValues_Click(Object sender, EventArgs e) {
        List<String> ids = Tree1.getSelectedNodeIds();
        if (!ids.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("选中的节点：");
            sb.append("<ul>");
            for (String nodeId : ids) {
                sb.append(String.format("<li>%s（%s）</li>", Tree1.findNode(nodeId).getText(), nodeId));
            }
            sb.append("</ul>");
            labResult.setTextRawHtml(new RawHtml(sb.toString()));
        } else {
            labResult.setText("没有选中的节点");
        }
    }

    public void btnSelectOthers_Click(Object sender, EventArgs e) {
        List<String> ids = new ArrayList<>(Tree1.getSelectedNodeIds());
        ids.add("hefei");
        ids.add("huangshan");
        Tree1.setSelectedNodeIds(ids);
    }
}
