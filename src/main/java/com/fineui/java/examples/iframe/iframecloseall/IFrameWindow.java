package com.fineui.java.examples.iframe.iframecloseall;

import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 子页一（路由 {@code iframe/iframe-close-all/iframe-window}）：内含再一层 {@code Window3}/{@code Window4}。
 * 内层窗体的关闭事件里更新本页 {@code labResult} 后经 {@code ActiveWindow.HidePostBack()} 向上传递——
 * 由此「全部关闭」能沿层级一次性关闭所有 IFrame 窗体。
 */
@FineUIPage("iframe/iframe-close-all/iframe-window")
public class IFrameWindow extends PageBase {

    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Window3_Close(Object sender, EventArgs e) {
        labResult.setText("Window3 关闭了，时间：" + nowTime());
        ActiveWindow.hidePostBack();
    }

    public void Window4_Close(Object sender, EventArgs e) {
        labResult.setText("Window4 关闭了，时间：" + nowTime());
        ActiveWindow.hidePostBack();
    }

    private static String nowTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
