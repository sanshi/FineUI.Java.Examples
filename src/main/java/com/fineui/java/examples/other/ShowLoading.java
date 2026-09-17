package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 面板加载动画演示页面模型类（路由 {@code other/show-loading}）：窗体内的「刷新」按钮纯客户端调用
 * {@code F.ui.Window1.showLoading(0.8)} 显示加载遮罩、1 秒后 {@code hideLoading} 并切换窗体内容；
 * 页面 ready 时自动执行一次刷新。无服务端事件。
 */
@FineUIPage("other/show-loading")
public class ShowLoading extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
