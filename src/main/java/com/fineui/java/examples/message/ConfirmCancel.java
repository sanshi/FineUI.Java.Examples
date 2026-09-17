package com.fineui.java.examples.message;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 响应确定按钮演示页（路由 {@code message/confirm-cancel}）：三种「先确认再操作」的写法——
 * ① 按钮的 {@code confirm-text} 属性（点击先弹确认框，确认后才回发服务端事件）；
 * ② 客户端 {@code F.confirm} 只带确定回调；③ 客户端 {@code F.confirm} 同时带确定与取消回调
 * （取消也触发后台自定义事件）。后台统一入口 {@code Page_CustomEvent} 按事件名分派提示。
 */
@FineUIPage("message/confirm-cancel")
public class ConfirmCancel extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        String name = e.getEventName();
        if ("Operation2".equals(name)) {
            showNotify("执行了操作二！");
        } else if ("Operation3_ok".equals(name)) {
            showNotify("执行了操作三！");
        } else if ("Operation3_cancel".equals(name)) {
            showNotify("取消执行操作三！");
        }
    }

    public void btnOperation1_Click(Object sender, EventArgs e) {
        showNotify("执行了操作一！");
    }
}
