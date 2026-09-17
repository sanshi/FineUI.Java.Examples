package com.fineui.java.examples.iframe.iframeiframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 子页一（路由 {@code iframe/iframe-iframe/iframe-window}）：父页 {@code Window1} 的子页，内含再一层
 * {@code Window3}/{@code Window4}（形成 IFrame 套 IFrame）。「回发弹出窗体」按钮
 * （{@code click-handler="onCloseActiveWindowPostBackClick"}）关闭父页 {@code Window1} 并回发。
 */
@FineUIPage("iframe/iframe-iframe/iframe-window")
public class IFrameWindow extends PageBase {

    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Window3_Close(Object sender, EventArgs e) {
        labResult.setText("Window3 关闭了，时间：" + nowTime());
    }

    public void Window4_Close(Object sender, EventArgs e) {
        labResult.setText("Window4 关闭了，时间：" + nowTime());
    }

    private static String nowTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
