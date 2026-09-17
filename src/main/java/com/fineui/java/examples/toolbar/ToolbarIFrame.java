package com.fineui.java.examples.toolbar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 工具栏与内嵌页面演示页（路由 {@code toolbar/toolbar-iframe}）：面板内嵌一个 iframe，工具栏按钮点击后
 * 在该 iframe 中打开不同页面（客户端脚本：直接设置面板 iframe 地址，或用命名 iframe 打开）。
 */
@FineUIPage("toolbar/toolbar-iframe")
public class ToolbarIFrame extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
