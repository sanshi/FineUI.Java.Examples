package com.fineui.java.examples.message;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义对话框按钮文本演示页（路由 {@code message/confirm-buttons}）：纯客户端页面，用
 * {@code F.create({type: 'MessageBox', buttons: [...]})} 把默认「确定/取消」换成自定义文本
 * （「直接退出/不退出」），点击按钮按 {@code buttonId} 触发不同后台自定义事件
 * （{@code Page_CustomEvent} 分派 {@code ConfirmOK} / {@code ConfirmCancel} 提示）。
 */
@FineUIPage("message/confirm-buttons")
public class ConfirmButtons extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("ConfirmOK".equals(e.getEventName())) {
            showNotify("你点击了[直接退出]按钮！");
        } else if ("ConfirmCancel".equals(e.getEventName())) {
            showNotify("你点击了[不退出]按钮！");
        }
    }
}
