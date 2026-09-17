package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 服务器错误（自行拦截）演示页面模型类（路由 {@code other/server-error-custom}）：点击按钮触发服务端异常
 * （500），页面用 {@code F.beforeAjaxError} 钩子自定义错误处理——解析返回内容并弹自定义 alert，
 * 返回 false 阻止默认错误窗体。
 */
@FineUIPage("other/server-error-custom")
public class ServerErrorCustom extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button5_Click(Object sender, EventArgs e) {
        throw new RuntimeException("服务器异常错误！");
    }
}
