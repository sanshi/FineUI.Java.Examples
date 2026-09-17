package com.fineui.java.examples.home;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 主题仓库页（路由 {@code home/themes}）：首页“主题仓库”弹窗以 IFrame 加载本页，点击主题色块即写 Theme
 * cookie 并刷新顶层窗口应用主题（纯静态内容，无服务端逻辑）。
 */
@FineUIPage("home/themes")
public class Themes extends PageBase {
}
