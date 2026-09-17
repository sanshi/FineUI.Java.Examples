package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 双击关闭选项卡（路由 {@code tab-strip/close-on-dblclick}）：TabStrip 开启 {@code close-on-dbl-click}，
 * 双击选项卡即可关闭；标签五单独关闭该特性（Tab 的同名属性覆盖 TabStrip）。
 */
@FineUIPage("tab-strip/close-on-dblclick")
public class CloseOnDblclick extends PageBase {

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
