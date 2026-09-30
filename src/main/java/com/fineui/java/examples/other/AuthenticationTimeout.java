package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 拦截服务器端重定向演示页面模型类（路由 {@code other/authentication-timeout}）：Button2 回发时服务端
 * {@link #redirect(String)} 产生跳转命令；页面钩子从回发 JSON 原文读取命令并弹原生确认框。
 * 取消时返回 false，阻止整份响应应用并留在本页。
 */
@FineUIPage("other/authentication-timeout")
public class AuthenticationTimeout extends PageBase {

    /** 在严格 CSP 下验证同步确认及默认导航。 */
    public void Page_Get(Object sender, EventArgs e) {
        getPageManager().cspScripts(true).cspScriptsAllowNonce(true);
    }

    /** 模拟 Forms Authentication 超时：服务端重定向到首页（携带 ReturnUrl，指向本页的 Java kebab 路由）。 */
    public void Button2_Click(Object sender, EventArgs e) {
        redirect("/?ReturnUrl=%2fother%2fauthentication-timeout");
    }
}
