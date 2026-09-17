package com.fineui.java.examples.iframe.iframecloseall;

import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 子页二（路由 {@code iframe/iframe-close-all/iframe-window2}）：最内层页面。「全部关闭」按钮
 * （{@code click-handler="onCloseActiveWindowPostBackClick"}）或服务端回发（{@code btnCloseServer_Click}）
 * 都会向上逐层关闭所有 IFrame 窗体并回发到顶层。
 */
@FineUIPage("iframe/iframe-close-all/iframe-window2")
public class IFrameWindow2 extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnCloseServer_Click(Object sender, EventArgs e) {
        ActiveWindow.hidePostBack();
    }
}
