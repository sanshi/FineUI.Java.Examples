package com.fineui.java.examples.mobile.panel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 移动端面板（切换面板）演示页（路由 {@code mobile/panel/panel-slide}）：两个视口面板间用
 * {@code F.slideLeft}/{@code F.slideRight} 左右滑动切换。全部为声明式静态内容，切换逻辑在客户端。
 */
@FineUIPage("mobile/panel/panel-slide")
public class PanelSlide extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
