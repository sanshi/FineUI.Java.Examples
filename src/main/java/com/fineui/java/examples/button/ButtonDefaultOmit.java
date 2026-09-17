package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 演示页（路由 {@code button/button-default-omit}）：模板声明「默认值」的枚举属性首屏不产出对应 wire key、
 * 声明非默认才产出。纯静态展示，无服务端事件；用于「查看网页源代码」核对 {@code F.render} 载荷。
 */
@FineUIPage("button/button-default-omit")
public class ButtonDefaultOmit extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
