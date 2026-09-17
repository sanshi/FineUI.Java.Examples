package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体综合演示页（路由 {@code window/window}）：演示服务端/客户端显示与隐藏窗体，以及窗体关闭事件
 * （{@code close-action="HidePostBack"} 时点关闭按钮或按 ESC 会触发服务端 {@code on-close} 处理）。
 */
@FineUIPage("window/window")
public class Window extends PageBase {

    com.fineui.java.core.controls.Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 窗体关闭事件：弹出消息框提示。 */
    public void Window1_Close(Object sender, EventArgs e) {
        showAlert("触发了窗体的关闭事件！");
    }

    /** 服务端显示窗体。 */
    public void btnShowInServer_Click(Object sender, EventArgs e) {
        Window1.setHidden(false);
    }

    /** 服务端隐藏窗体。 */
    public void btnHideInServer_Click(Object sender, EventArgs e) {
        Window1.setHidden(true);
    }
}
