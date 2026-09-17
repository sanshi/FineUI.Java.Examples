package com.fineui.java.examples.panel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Panel;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义工具前置 演示页（路由 {@code panel/tools-custom-tool-ahead}）：面板标题栏工具，点击弹出通知；底部按钮在服务端读取折叠状态并提示。
 */
@FineUIPage("panel/tools-custom-tool-ahead")
public class ToolsCustomToolAhead extends PageBase {

    Panel Panel1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 读取面板当前折叠状态并提示。 */
    public void Button2_Click(Object sender, EventArgs e) {
        showNotify("面板处于" + (Panel1.isCollapsed() ? "折叠" : "展开") + "状态");
    }
}
