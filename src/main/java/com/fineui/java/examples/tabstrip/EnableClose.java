package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 选项卡关闭图标与右键菜单（路由 {@code tab-strip/enable-close}）：TabStrip 开启右键关闭菜单，
 * 各选项卡通过 {@code enable-close} 单独控制是否显示关闭图标；提供客户端与服务端两组按钮，
 * 演示显示 / 显示并激活 / 隐藏标签三（含移动到尾部）。
 */
@FineUIPage("tab-strip/enable-close")
public class EnableClose extends PageBase {

    com.fineui.java.core.controls.TabStrip TabStrip1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 显示标签三（服务端代码）。 */
    public void btnShowInServer_Click(Object sender, EventArgs e) {
        TabStrip1.showTab("Tab3");
    }

    /** 显示并激活标签三（服务端代码）。 */
    public void btnShowActiveInServer_Click(Object sender, EventArgs e) {
        TabStrip1.activateTab("Tab3");
    }

    /** 隐藏标签三（服务端代码）。 */
    public void btnHideInServer_Click(Object sender, EventArgs e) {
        TabStrip1.hideTab("Tab3");
    }
}
