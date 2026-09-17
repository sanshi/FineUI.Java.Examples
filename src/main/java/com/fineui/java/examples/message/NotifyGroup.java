package com.fineui.java.examples.message;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 通知对话框（分组显示）演示页（路由 {@code message/notify-group}）：纯客户端页面，多次点击按钮经
 * {@code showNotifyGroup}（来自 {@code res/js/notify_group.js}）堆叠多个通知框，新通知排在已有通知的<b>下方</b>。
 */
@FineUIPage("message/notify-group")
public class NotifyGroup extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
