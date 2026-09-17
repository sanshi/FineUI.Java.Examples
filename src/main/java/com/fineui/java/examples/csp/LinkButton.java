package com.fineui.java.examples.csp;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * CSP 链接按钮与超链接（路由 {@code csp/link-button}）：演示严格 CSP（启用 nonce、不含 {@code 'unsafe-inline'}）下
 * LinkButton / HyperLink 正常工作、且不产生任何内联事件处理器。
 *
 * <p>客户端点击逻辑使用 {@code click-handler} 或具名 {@code <f:listener>}，无需 {@code 'unsafe-eval'}。
 * 服务端点击回发由桥接层监听 {@code click} 事件织入。二者都不产生内联 {@code onclick}。
 */
@FineUIPage("csp/link-button")
public class LinkButton extends PageBase {

    /** 渲染前配置 CSP：开启 + 启用 nonce（须放 Page_Get——响应头与 nonce 都要早于页面渲染）。 */
    public void Page_Get(Object sender, EventArgs e) {
        getPageManager().cspScripts(true).cspScriptsAllowNonce(true);
    }

    public void lbServer_Click(Object sender, EventArgs e) {
        showNotify("你点击了链接按钮（服务器端事件）");
    }

    public void lbConfirm_Click(Object sender, EventArgs e) {
        showNotify("确认通过，已回发");
    }
}
