package com.fineui.java.examples.panel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.GroupPanel;
import com.fineui.java.examples.code.PageBase;

/**
 * 分组面板演示页（路由 {@code panel/group}）：面板内放置多个可折叠的分组面板，分别承载表单与富文本内容；
 * 底部按钮在服务端切换第二个分组面板的折叠状态。
 */
@FineUIPage("panel/group")
public class Group extends PageBase {

    GroupPanel GroupPanel2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 切换分组面板二的折叠/展开。 */
    public void Button2_Click(Object sender, EventArgs e) {
        GroupPanel2.setCollapsed(!GroupPanel2.isCollapsed());
    }
}
