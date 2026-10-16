package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.PageContext;
import com.fineui.java.examples.code.PageBase;

/**
 * 拦截回发跳转（异步确认）的演示页面模型类（路由 {@code other/authentication-timeout-async}）：
 * 与 {@link AuthenticationTimeout} 同场景，但页面钩子用<b>异步</b> {@code F.confirm}（FineUI 对话框）
 * 询问是否跳转。钩子返回 false 阻止整份响应应用，点「确定」时由页面脚本
 * 从回发 JSON 原文中读取 URL 并直接导航；点「取消」留在本页。
 */
@FineUIPage("other/authentication-timeout-async")
public class AuthenticationTimeoutAsync extends PageBase {

    /** 模拟登录超时：登记跳到首页的命令，ReturnUrl 指向本页的 Java 路由。 */
    public void Button2_Click(Object sender, EventArgs e) {
        PageContext.redirect("/?ReturnUrl=%2fother%2fauthentication-timeout-async");
    }
}
