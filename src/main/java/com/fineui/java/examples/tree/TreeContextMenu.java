package com.fineui.java.examples.tree;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.examples.code.PageBase;

/**
 * 节点右键菜单（路由 {@code tree/tree-context-menu}）：在树节点上点右键弹出自定义菜单（阻止浏览器默认菜单），
 * 叶子节点上菜单项禁用；菜单项"展开全部/折叠全部"作用于当前右键的节点。
 * 后台按钮切换右键是否先选中节点，不取消业务菜单。
 */
@FineUIPage("tree/tree-context-menu")
public class TreeContextMenu extends PageBase {
    Tree Tree1;
    Label labContextSelection;

    public void btnToggleContextSelection_Click(Object sender, EventArgs e) {
        // 只改变右键是否先选中节点，菜单继续作用于实际右键的节点。
        Tree1.setClickOnContextMenu(!Tree1.isClickOnContextMenu());
        labContextSelection.setText(Tree1.isClickOnContextMenu() ? "右键选中节点：开启" : "右键选中节点：关闭");
    }
}
