package com.fineui.java.examples.iframe.parentwindowclose;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 页二（路由 {@code iframe/parent-window-close/iframe-window1}）：嵌在顶层父页 {@code Panel1} 的 iframe 中，
 * 内含再一层 {@code Window1}（其子页为页三）。页三关闭 {@code Window1} 回发到本页
 * {@code IFrameWindow1_Window1_Close}：更新本页 {@code labResult} 后经
 * {@code invokeParentFunction("updateLabelResult")} 级联更新顶层父页 {@code labResult}
 * （无 eval：用结构化命令按名调用父页全局函数）。
 */
@FineUIPage("iframe/parent-window-close/iframe-window1")
public class IFrameWindow1 extends PageBase {

    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        labResult.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    public void IFrameWindow1_Window1_Close(Object sender, EventArgs e) {
        // IFrameWindow1 -> labResult
        labResult.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        // 调用父页面定义的函数 updateLabelResult
        invokeParentFunction("updateLabelResult");
    }
}
