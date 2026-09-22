package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体最大化（注册初始脚本）演示页（路由 {@code window/maximized-script}）：窗体本身不设置
 * {@code maximized}，而是页面加载后用客户端脚本 {@code F.ui.Window1.maximize()} 使其初始最大化；
 * 关闭后再次显示则恢复为初始尺寸（与 {@code maximized="true"} 的行为差异见页面文字说明）。
 */
@FineUIPage("window/maximized-script")
public class MaximizedScript extends PageBase {

    com.fineui.java.core.controls.Window Window1;

    public void btnServerMaximize_Click(Object sender, EventArgs e) {
        Window1.maximize();
    }

    public void btnServerRestore_Click(Object sender, EventArgs e) {
        Window1.restore();
    }

    public void btnServerClose_Click(Object sender, EventArgs e) {
        Window1.close();
    }

    public void Page_Load(Object sender, EventArgs e) {
    }
}
