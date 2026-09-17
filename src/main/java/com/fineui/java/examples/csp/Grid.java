package com.fineui.java.examples.csp;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * CSP 表格（路由 {@code csp/grid}）：在内容安全策略（CSP）下渲染表格，并从白名单 CDN
 * （jsdelivr / unpkg）加载外部 underscore 库、执行带 {@code nonce} 的内联脚本。
 *
 * <p>页面开启 CSP + {@code nonce}，同时用 {@code cspScriptsAllowUrls} 放行外部脚本域；
 * 表格数据绑定与列渲染正常，依赖 underscore 的「选中了哪些行」提示也能正常工作——
 * 证明 FineUI.Java 在「同源 + nonce + 外部域白名单」的 CSP 下功能完整。
 */
@FineUIPage("csp/grid")
public class Grid extends PageBase {

    // 字段名与模板 id 一致；用全限定名避免与页面类名 Grid 冲突。
    com.fineui.java.core.controls.Grid Grid1;

    /** 渲染前配置 CSP：开启 + nonce + 放行 underscore 外部脚本域（响应头须早于页面渲染）。 */
    public void Page_Get(Object sender, EventArgs e) {
        getPageManager().cspScripts(true).cspScriptsAllowNonce(true)
                .cspScriptsAllowUrls("cdn.jsdelivr.net", "unpkg.com");
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 内存数据：首屏绑定一次，数据由客户端保管，回发无需重绑。
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
