package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 站点快速导航演示页面模型类（路由 {@code other/nav}）：图标导航面板，每个按钮通过
 * {@code top.addExampleTabByHref('<url>')} 向父框架打开/复用选项卡（纯客户端）。无服务端事件。
 */
@FineUIPage("other/nav")
public class Nav extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
