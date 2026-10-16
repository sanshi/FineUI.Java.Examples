package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.PageContext;
import com.fineui.java.examples.code.PageBase;

/**
 * 拦截回发跳转的演示页面模型类（路由 {@code other/authentication-timeout}）：Button2 回发时服务端
 * {@link PageContext#redirect(String)} 登记客户端跳转命令；页面钩子从回发 JSON 原文读取命令并弹原生确认框。
 * 取消时返回 false，阻止整份响应应用并留在本页。
 */
@FineUIPage("other/authentication-timeout")
public class AuthenticationTimeout extends PageBase {

    /** 模拟登录超时：登记跳到首页的命令，ReturnUrl 指向本页的 Java 路由。 */
    public void Button2_Click(Object sender, EventArgs e) {
        PageContext.redirect("/?ReturnUrl=%2fother%2fauthentication-timeout");
    }
}
