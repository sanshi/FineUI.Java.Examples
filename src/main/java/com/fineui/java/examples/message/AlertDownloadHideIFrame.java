package com.fineui.java.examples.message;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 响应确定按钮（点击确定按钮后先隐藏 IFrame 窗体再下载文件）演示页（路由
 * {@code message/alert-download-hide-iframe}）：本页是一个 <b>EnableIFrame</b> 的窗体（{@code Window2}），
 * 窗体内嵌子页 {@code iframe-window}。子页按钮回发时隐藏激活窗体（即本页的 {@code Window2}）并调用
 * 本页的 {@code showConfirm} 函数弹确认框——点确定跳转下载地址，点取消触发后台自定义事件
 * （{@code Page_CustomEvent} 分派 {@code ConfirmCancel} 提示）。
 */
@FineUIPage("message/alert-download-hide-iframe")
public class AlertDownloadHideIFrame extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("ConfirmCancel".equals(e.getEventName())) {
            showNotify("点击了取消按钮！");
        }
    }
}
