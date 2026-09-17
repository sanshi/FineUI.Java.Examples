package com.fineui.java.examples.message;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 通知对话框（分组显示，最新的在最上面）演示页（路由 {@code message/notify-group-newest-top}）：
 * 纯客户端页面，多次点击按钮经 {@code showNotifyGroup}（来自 {@code res/js/notify_group.js}）堆叠多个通知框，
 * 与 {@link NotifyGroup} 相反，新通知插到最<b>上方</b>（已有通知整体下移）。
 */
@FineUIPage("message/notify-group-newest-top")
public class NotifyGroupNewestTop extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
