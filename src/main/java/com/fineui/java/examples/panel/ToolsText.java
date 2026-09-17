package com.fineui.java.examples.panel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Panel;
import com.fineui.java.examples.code.PageBase;

/**
 * 面板标题栏工具演示页（路由 {@code panel/tools-text}）：面板标题栏放置一排工具图标，点击弹出通知；
 * 底部按钮在服务端读取面板折叠状态并提示。
 */
@FineUIPage("panel/tools-text")
public class ToolsText extends PageBase {

    Panel Panel1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 读取面板当前折叠状态并提示。 */
    public void Button2_Click(Object sender, EventArgs e) {
        showNotify("面板处于" + (Panel1.isCollapsed() ? "折叠" : "展开") + "状态");
    }
}
