package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 拦截服务器端重定向（异步确认）演示页面模型类（路由 {@code other/authentication-timeout-async}）：
 * 与 {@link AuthenticationTimeout} 同场景，但页面钩子用<b>异步</b> {@code F.confirm}（FineUI 对话框）
 * 询问是否跳转。钩子总是返回 false 阻止桥接层自动执行跳转，点「确定」时由页面脚本
 * 读取钩子提供的 URL 直接导航，不编译脚本文本；点「取消」留在本页。
 */
@FineUIPage("other/authentication-timeout-async")
public class AuthenticationTimeoutAsync extends PageBase {

    /** 在严格 CSP 下验证异步确认及导航，模板内联脚本由框架自动添加 nonce。 */
    public void Page_Get(Object sender, EventArgs e) {
        getPageManager().cspScripts(true).cspScriptsAllowNonce(true);
    }

    /** 模拟 Forms Authentication 超时：服务端重定向到首页（携带 ReturnUrl，指向本页的 Java kebab 路由）。 */
    public void Button2_Click(Object sender, EventArgs e) {
        redirect("/?ReturnUrl=%2fother%2fauthentication-timeout-async");
    }
}
