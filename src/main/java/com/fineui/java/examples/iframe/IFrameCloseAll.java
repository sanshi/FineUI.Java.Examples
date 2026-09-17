package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 全部关闭 IFrame 窗体（路由 {@code iframe/iframe-close-all}）：最内层 IFrame 子页的「全部关闭」
 * （{@code F.activeWindow.hidePostBack()}）会沿层级一次性关闭所有 IFrame 窗体——既关闭内层
 * {@code Window3}，也连带关闭顶层 {@code Window1}，并回发使顶层 {@code labResult} 更新。
 */
@FineUIPage("iframe/iframe-close-all")
public class IFrameCloseAll extends PageBase {

    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        labResult.setText("页面加载时间：" + nowTime());
    }

    public void Window1_Close(Object sender, EventArgs e) {
        labResult.setText("Window1 关闭了，时间：" + nowTime());
    }

    public void Window2_Close(Object sender, EventArgs e) {
        labResult.setText("Window2 关闭了，时间：" + nowTime());
    }

    private static String nowTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
