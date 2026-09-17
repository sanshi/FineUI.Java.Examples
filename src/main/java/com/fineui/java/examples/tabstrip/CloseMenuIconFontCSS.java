package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 右键菜单项图标字体（CSS 方式，路由 {@code tab-strip/close-menu-icon-font-css}）：纯 CSS 为关闭菜单项的
 * 三种类（关闭 / 关闭其他 / 关闭全部）添加 FontAwesome 图标字体。
 */
@FineUIPage("tab-strip/close-menu-icon-font-css")
public class CloseMenuIconFontCSS extends PageBase {

    com.fineui.java.core.controls.TabStrip TabStrip1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 显示标签三（服务端代码）。 */
    public void btnShowInServer_Click(Object sender, EventArgs e) {
        TabStrip1.showTab("Tab3");
    }

    /** 隐藏标签三（服务端代码）。 */
    public void btnHideInServer_Click(Object sender, EventArgs e) {
        TabStrip1.hideTab("Tab3");
    }
}
