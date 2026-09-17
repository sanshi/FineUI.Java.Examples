package com.fineui.java.examples.message;

import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * IFrame 子页（路由 {@code message/alert-download-hide-iframe/iframe-window}）：本页被父页
 * {@code AlertDownloadHideIFrame} 的 EnableIFrame 窗体嵌入。按钮回发时<b>隐藏激活窗体</b>
 * （即父页中打开本子页的窗体 {@code Window2}）并调用<b>父页面</b>的 {@code showConfirm} 函数弹确认框
 * （确认框须在父页弹出，因为本子页随即被关闭）。
 */
@FineUIPage("message/alert-download-hide-iframe/iframe-window")
public class IFrameWindow extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnOperation_Click(Object sender, EventArgs e) {
        // 先隐藏激活窗体（父页 Window2），再调用父页的 showConfirm 函数
        ActiveWindow.hide();
        invokeParentFunction("showConfirm");
    }
}
