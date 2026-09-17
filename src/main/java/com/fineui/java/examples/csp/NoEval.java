package com.fineui.java.examples.csp;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 无需 eval 的严格 CSP（路由 {@code csp/no-eval}）：演示 FineUI.Java 能在<b>最严格</b>的
 * {@code script-src 'self' 'nonce-xxx'} 下全功能运行——不放行 {@code 'unsafe-inline'}、更不放行 {@code 'unsafe-eval'}。
 *
 * <p>要点：FineUI.Java 回发是纯 JSON、无 {@code eval}/{@code new Function}，所以 CSP 里根本不需要 {@code 'unsafe-eval'}。
 * 本页在此策略下：① 按钮点击回发正常（证明框架功能完整）；② 页面里尝试 {@code eval(...)} / {@code new Function(...)}
 * 会被浏览器<b>直接拦截并抛错</b>（证明 eval 类注入在此策略下无法得逞）。
 */
@FineUIPage("csp/no-eval")
public class NoEval extends PageBase {

    /** 渲染前配置最严格 CSP：仅同源 + nonce；不开 unsafe-inline，也永不产出 unsafe-eval。 */
    public void Page_Get(Object sender, EventArgs e) {
        getPageManager().cspScripts(true).cspScriptsAllowNonce(true);
    }

    public void btnPostback_Click(Object sender, EventArgs e) {
        showNotify("回发成功——FineUI.Java 全程无 eval，严格 CSP（无 'unsafe-eval'）下功能完整");
    }
}
