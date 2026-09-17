package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 服务器错误（简单错误）演示页面模型类（路由 {@code other/server-error-simple}）：点击按钮触发服务端异常
 * （500），页面未自定义 beforeAjaxError 钩子，桥接层按默认错误处理弹出错误窗体（标题 Internal Server Error）。
 *
 * <p>注：「简单错误」形态（默认错误处理降级为 F.alert 简讯的 simpleError 配置）尚未实现，
 * 当前行为与 {@link ServerError} 等价（默认错误窗体）；若需区分可后续在 FineUIConfig/桥接层补 simpleError。
 */
@FineUIPage("other/server-error-simple")
public class ServerErrorSimple extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button5_Click(Object sender, EventArgs e) {
        throw new RuntimeException("服务器异常错误！");
    }
}
