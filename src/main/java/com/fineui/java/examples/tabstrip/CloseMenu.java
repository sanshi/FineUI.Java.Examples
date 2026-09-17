package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 选项卡右键菜单新增菜单项（路由 {@code tab-strip/close-menu}）：在选项卡右键关闭菜单中，
 * 通过客户端脚本 {@code getCloseMenu().add([...])} 追加一个自定义菜单项；点击时提示所属选项卡标题。
 */
@FineUIPage("tab-strip/close-menu")
public class CloseMenu extends PageBase {

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
