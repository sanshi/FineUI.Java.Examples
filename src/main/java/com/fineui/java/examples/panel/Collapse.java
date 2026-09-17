package com.fineui.java.examples.panel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Panel;
import com.fineui.java.examples.code.PageBase;

/**
 * 面板折叠展开演示页（路由 {@code panel/collapse}）：两个嵌套面板都可折叠展开，折叠/展开时触发服务端事件，
 * 弹出通知提示当前所处状态。
 */
@FineUIPage("panel/collapse")
public class Collapse extends PageBase {

    Panel Panel1;
    Panel Panel2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 面板一折叠/展开时提示当前状态。 */
    public void Panel1_CollapseExpand(Object sender, EventArgs e) {
        showNotify("面板一处于" + (Panel1.isCollapsed() ? "折叠" : "展开") + "状态");
    }

    /** 面板二折叠/展开时提示当前状态。 */
    public void Panel2_CollapseExpand(Object sender, EventArgs e) {
        showNotify("面板二处于" + (Panel2.isCollapsed() ? "折叠" : "展开") + "状态");
    }
}
