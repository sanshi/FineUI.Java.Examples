package com.fineui.java.examples.tree;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.examples.code.PageBase;

/**
 * 节点点击（回发）（路由 {@code tree/tree-node-click}）：客户端 {@code nodeclick} 监听器判断节点，
 * 西平县先弹原生确认框，指定节点调 {@code F.customEvent('Tree1_NodeClick', nodeId)} 触发后台自定义事件。
 *
 * <p>后台统一入口 {@code Page_CustomEvent} 按事件名分派：{@code Tree1_NodeClick} 时把点击的节点信息回填到 Label。
 */
@FineUIPage("tree/tree-node-click")
public class TreeNodeClick extends PageBase {

    Tree Tree1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Tree1_NodeClick".equals(e.getEventName())) {
            String nodeId = e.getArgument();
            labResult.setText(String.format("你点击了树节点：%s（%s）", nodeId, Tree1.findNode(nodeId).getText()));
        }
    }
}
