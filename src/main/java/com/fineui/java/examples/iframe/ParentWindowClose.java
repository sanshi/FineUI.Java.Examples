package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 关闭子窗体回写父页面（路由 {@code iframe/parent-window-close}）：三层结构（父页 -> 面板
 * {@code Panel1} 内 {@code IFrameWindow1}（页二）-> 页二的 {@code Window1} 内 {@code IFrameWindow2}（页三））。
 * 页三按钮「关闭本窗体，回发父页面和父页面的父页面」（{@code ActiveWindow.HidePostBack()}）关闭页二的窗体，
 * 触发页二 {@code OnClose} 更新页二 {@code labResult}，并经 {@code parent.updateLabelResult()} 级联更新
 * 顶层父页 {@code labResult}。
 */
@FineUIPage("iframe/parent-window-close")
public class ParentWindowClose extends PageBase {

    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        labResult.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
