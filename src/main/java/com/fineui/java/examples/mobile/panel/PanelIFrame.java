package com.fineui.java.examples.mobile.panel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 移动端面板（IFrame）演示页（路由 {@code mobile/panel/panel-iframe}）：面板内嵌 IFrame，
 * 底部菜单按钮切换 IFrame 地址。全部为声明式静态内容，切换逻辑在客户端，无服务端逻辑。
 */
@FineUIPage("mobile/panel/panel-iframe")
public class PanelIFrame extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
