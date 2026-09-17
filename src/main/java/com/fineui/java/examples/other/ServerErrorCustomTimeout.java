package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * AJAX 请求超时错误（自行拦截）演示页面模型类（路由 {@code other/server-error-custom-timeout}）：
 * 页面把全局 AJAX 超时设为 2 秒（经
 * {@code getPageManager().set("ajaxTimeout", 2)} 下发 F.init 配置），按钮回发时服务端故意延迟 5 秒
 * 再抛异常，客户端 2 秒超时 → 触发 {@code beforeAjaxError(data, 'timeout')} 钩子 → 页面显示居中通知。
 */
@FineUIPage("other/server-error-custom-timeout")
public class ServerErrorCustomTimeout extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // AJAX 请求超时设为 2 秒
            getPageManager().set("ajaxTimeout", 2);
        }
    }

    public void Button5_Click(Object sender, EventArgs e) {
        // 故意延迟 5 秒，以便客户端 AJAX 请求超时
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        throw new RuntimeException("服务器异常错误！");
    }
}
