package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 按钮弹出 IFrame 窗体（路由 {@code iframe/button-iframe}）：在父页面弹出窗体二
 * （{@code Target=Parent}、{@code CloseAction=HidePostBack}），点右上角关闭图标会触发服务端
 * {@code OnClose} 事件并回发；窗体一（{@code Target=Self}）默认只是隐藏窗体、不回发。
 */
@FineUIPage("iframe/button-iframe")
public class ButtonIFrame extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Window1_Close(Object sender, EventArgs e) {
        showNotify("Window1 被关闭了！");
    }

    public void Window2_Close(Object sender, EventArgs e) {
        showNotify("Window2 被关闭了！");
    }
}
