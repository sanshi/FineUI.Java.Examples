package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 拦截服务器端重定向演示页面模型类（路由 {@code other/authentication-timeout}）：Button2 回发时服务端
 * {@link #redirect(String)} 产生跳转（桥接层把
 * redirect 命令构造成 {@code window.location.href='...';} 响应文本，触发 {@code beforeAjaxSuccess} 钩子，
 * 页面钩子可弹原生确认框拦截；取消时钩子返回 false 阻止跳转、留在本页）。
 */
@FineUIPage("other/authentication-timeout")
public class AuthenticationTimeout extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 模拟 Forms Authentication 超时：服务端重定向到首页（携带 ReturnUrl，指向本页的 Java kebab 路由）。 */
    public void Button2_Click(Object sender, EventArgs e) {
        redirect("/?ReturnUrl=%2fother%2fauthentication-timeout");
    }
}
