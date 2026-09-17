package com.fineui.java.examples.message;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 设置自动获取焦点的按钮演示页（路由 {@code message/confirm-buttons-auto-focus}）：在自定义按钮文本
 * 消息框（见 {@link ConfirmButtons}）基础上设置 {@code autoFocusButtonId: 'cancel'}——弹框后焦点落在
 * 「不退出」按钮（而非默认第一个主按钮「直接退出」）。
 */
@FineUIPage("message/confirm-buttons-auto-focus")
public class ConfirmButtonsAutoFocus extends PageBase {

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
