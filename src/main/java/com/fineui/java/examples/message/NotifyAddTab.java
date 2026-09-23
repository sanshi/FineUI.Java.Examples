package com.fineui.java.examples.message;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.enums.Position;
import com.fineui.java.core.enums.Target;
import com.fineui.java.examples.code.PageBase;

import java.util.UUID;

/**
 * 通知对话框（向父页面添加选项卡）演示页（路由 {@code message/notify-add-tab}）：点击按钮回发后弹出
 * 一个<b>常驻</b>通知框（不自动消失、无标题栏），框内链接通过页面事件监听调用父页面
 * {@code addExampleTab} 添加选项卡
 * 并随即按通知框 ID 隐藏该通知。本页只在示例框架页（父页面）中运行时链接才有效。
 */
@FineUIPage("message/notify-add-tab")
public class NotifyAddTab extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnOperation1_Click(Object sender, EventArgs e) {
        // 随机生成通知框的客户端 ID，通过 data 属性传给委托的点击处理函数。
        String notifyId = UUID.randomUUID().toString();
        String html = "<div class=\"addtabcontainer\"><a class=\"addtablink\" href=\"#\" data-notify-id=\""
                + notifyId + "\">向父页面添加选项卡</a></div>";
        showNotifyRaw(html, MessageBoxIcon.None, notifyId, Position.Right, Position.Bottom, 0, false);
    }
}
