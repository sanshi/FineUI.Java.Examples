package com.fineui.java.examples.tree;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.examples.code.PageBase;

/**
 * 单节点展开（路由 {@code tree/tree-single-expand}）：EnableSingleExpand=true 同级同时只展开一个节点，
 * EnableSingleClickExpand=false 关闭单击展开。
 */
@FineUIPage("tree/tree-single-expand")
public class TreeSingleExpand extends PageBase {
    Tree Tree1;
    Label labExpandOptions;

    public void btnToggleExpandOptions_Click(Object sender, EventArgs e) {
        // 只切换后续交互规则，现有节点与展开状态保留。
        boolean enabled = !Tree1.isEnableSingleExpand();
        Tree1.setEnableSingleExpand(enabled);
        Tree1.setExpandOnDblClick(enabled);
        String status = enabled ? "开启" : "关闭";
        labExpandOptions.setText("同级单节点展开：" + status + "；双击展开：" + status);
    }
}
