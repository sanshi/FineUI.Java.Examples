package com.fineui.java.examples.iframe.iframeiframe;

import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 子页二（路由 {@code iframe/iframe-iframe/iframe-window2}）：最内层页面。「回发弹出窗体」按钮
 * （{@code click-handler="onCloseActiveWindowPostBackClick"}）或服务端回发（{@code btnCloseServer_Click}）
 * 关闭父页 {@code Window3} 并回发（只更新子页一 {@code labResult}，不向上传递）。
 */
@FineUIPage("iframe/iframe-iframe/iframe-window2")
public class IFrameWindow2 extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnCloseServer_Click(Object sender, EventArgs e) {
        ActiveWindow.hidePostBack();
    }
}
