package com.fineui.java.examples.message;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.enums.Target;
import com.fineui.java.examples.code.PageBase;

/**
 * 响应确定按钮（点击确定按钮后下载文件）演示页（路由 {@code message/alert-download}）：点击按钮回发后
 * 弹出确认框——点确定跳转到下载地址（页面函数 {@code confirmOKCallback}），点取消触发后台自定义事件
 * （{@code Page_CustomEvent} 分派 {@code ConfirmCancel} 提示）。
 */
@FineUIPage("message/alert-download")
public class AlertDownload extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("ConfirmCancel".equals(e.getEventName())) {
            showNotify("点击了取消按钮！");
        }
    }

    public void btnOperation_Click(Object sender, EventArgs e) {
        showConfirm("操作成功！点击确定按钮开始下载文件，点取消按钮弹出对话框",
                "", MessageBoxIcon.Question, Target.Self,
                "confirmOKCallback", "confirmCancelCallback",
                null, true, null, null, null);
    }
}
