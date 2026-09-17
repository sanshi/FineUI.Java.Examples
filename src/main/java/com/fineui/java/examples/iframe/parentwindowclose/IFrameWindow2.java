package com.fineui.java.examples.iframe.parentwindowclose;

import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 页三（路由 {@code iframe/parent-window-close/iframe-window2}）：页二 {@code Window1} 的 iframe 子页。
 * 「关闭本窗体，回发父页面和父页面的父页面」按钮经 {@code ActiveWindow.HidePostBack()} 关闭页二的窗体，
 * 回发触发页二 {@code OnClose}（{@code IFrameWindow1_Window1_Close}）。
 */
@FineUIPage("iframe/parent-window-close/iframe-window2")
public class IFrameWindow2 extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void IFrameWindow2_Button1_Click(Object sender, EventArgs e) {
        ActiveWindow.hidePostBack();
    }
}
