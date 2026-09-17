package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 多层 IFrame（路由 {@code iframe/iframe-iframe}）：父页 {@code Window1} 的子页内还能再弹出自己的
 * {@code Window3}（含再一层 iframe），形成 IFrame 套 IFrame；子页「回发弹出窗体」
 * （{@code F.activeWindow.hidePostBack()}）关闭父页 {@code Window1} 并回发 -> {@code OnClose=Window1_Close}
 * 把父页 {@code labResult} 改为「Window1 关闭了，时间：...」。
 */
@FineUIPage("iframe/iframe-iframe")
public class IFrameIFrame extends PageBase {

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
