package com.fineui.java.examples.panel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.GroupPanel;
import com.fineui.java.examples.code.PageBase;

/**
 * 分组面板（路由 {@code panel/group2}）：一个面板内两个可折叠分组面板——
 * 分组面板一初始折叠、分组面板二初始展开；点击标题或按钮可切换折叠状态。
 */
@FineUIPage("panel/group2")
public class Group2 extends PageBase {

    GroupPanel GroupPanel2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button2_Click(Object sender, EventArgs e) {
        GroupPanel2.setCollapsed(!GroupPanel2.isCollapsed());
    }
}
