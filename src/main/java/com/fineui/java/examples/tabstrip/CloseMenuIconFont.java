package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 右键菜单项图标字体（JS 方式，路由 {@code tab-strip/close-menu-icon-font}）：通过客户端脚本遍历
 * 关闭菜单项，按其 CSS 类为「关闭 / 关闭其他 / 关闭全部」分别设置图标字体或图标。
 */
@FineUIPage("tab-strip/close-menu-icon-font")
public class CloseMenuIconFont extends PageBase {

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
