package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 捕获全局 JavaScript 错误演示页面模型类（路由 {@code other/jserror}）：页面注册 {@code window.onerror}
 * 全局错误处理器，把任意 JS 错误用 {@code F.alert}（标题「JavaScript错误！」、error 图标）展示。
 * Button1 故意触发未定义变量错误（纯客户端）；Button4 服务端回发后按名调用页面全局函数
 * （invokeClientFunction 找不到函数时安全降级、不报错，仅作占位演示）。
 */
@FineUIPage("other/jserror")
public class JSError extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button4_Click(Object sender, EventArgs e) {
        // 无 eval：按名调用页面函数；页面 script 槽未定义 test 时安全降级（不执行）
        invokeClientFunction("test");
    }
}
