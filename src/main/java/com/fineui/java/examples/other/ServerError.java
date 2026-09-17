package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 服务器错误演示页面模型类（路由 {@code other/server-error}）：点击按钮触发服务端异常（500），
 * 使用 FineUI 默认错误处理——桥接层收到非 2xx 响应后弹错误窗体（标题 Internal Server Error）。
 */
@FineUIPage("other/server-error")
public class ServerError extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button5_Click(Object sender, EventArgs e) {
        throw new RuntimeException("服务器异常错误！");
    }
}
