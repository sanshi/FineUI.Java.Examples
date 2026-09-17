package com.fineui.java.examples.mobile.message;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.mobile.MobilePageBase;

/**
 * 移动端确认对话框演示页（路由 {@code mobile/message/confirm}）：前四个按钮回发弹出确认对话框（{@code F.confirm}）；
 * 后两个按钮客户端自建消息框（自定义按钮文本/宽度），点击结果经自定义事件回发并居中通知。
 */
@FineUIPage("mobile/message/confirm")
public class Confirm extends MobilePageBase {

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Button4_ConfirmResult".equals(e.getEventName())) {
            showCenterNotify("你点击了 Button4 对话框的 " + e.getArgument() + " 按钮");
        } else if ("Button5_ConfirmResult".equals(e.getEventName())) {
            showCenterNotify("你点击了 Button5 对话框的 " + e.getArgument() + " 按钮");
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showConfirm("您真的要执行删除操作吗？", "确认操作", MessageBoxIcon.Question);
    }

    public void Button2_Click(Object sender, EventArgs e) {
        // 按钮填满 + 标题居中 + 无关闭按钮
        showConfirm("您真的要执行删除操作吗？", "确认操作", MessageBoxIcon.Question,
                true, false, false, "center", false);
    }

    public void Button3_Click(Object sender, EventArgs e) {
        // 简洁按钮 + 取消按钮在前 + 标题居中 + 无关闭按钮
        showConfirm("您真的要执行删除操作吗？", "确认操作", MessageBoxIcon.Question,
                false, true, true, "center", false);
    }

    public void Button6_Click(Object sender, EventArgs e) {
        // 简洁按钮 + 标题居中 + 无关闭按钮
        showConfirm("您真的要执行删除操作吗？", "确认操作", MessageBoxIcon.Question,
                false, true, false, "center", false);
    }
}
