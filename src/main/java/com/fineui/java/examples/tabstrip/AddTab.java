package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.IconHelper;
import com.fineui.java.examples.code.PageBase;

/**
 * 动态创建 IFrame 选项卡（路由 {@code tab-strip/add-tab}）：分别用客户端代码和服务端代码动态添加/删除
 * IFrame 选项卡。客户端按钮直接调 {@code F.ui.TabStrip1.addTab/closeTab}；服务端按钮经运行时命令下发。
 */
@FineUIPage("tab-strip/add-tab")
public class AddTab extends PageBase {

    com.fineui.java.core.controls.TabStrip TabStrip1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 服务端添加 DeepSeek 官网选项卡。 */
    public void btnAddTab3_Click(Object sender, EventArgs e) {
        TabStrip1.addTab("dynamic_tab3", "https://deepseek.com/", "DeepSeek官网（服务端代码）",
                IconHelper.namedIconUrl("Application"), true);
    }

    /** 服务端添加 Spring 官网选项卡。 */
    public void btnAddTab4_Click(Object sender, EventArgs e) {
        TabStrip1.addTab("dynamic_tab4", "https://spring.io/", "Spring 官网（服务端代码）",
                IconHelper.namedIconUrl("ApplicationAdd"), true);
    }

    /** 服务端删除 DeepSeek 官网选项卡。 */
    public void btnRemoveTab3_Click(Object sender, EventArgs e) {
        TabStrip1.hideTab("dynamic_tab3");
    }

    /** 服务端删除 Spring 官网选项卡。 */
    public void btnRemoveTab4_Click(Object sender, EventArgs e) {
        TabStrip1.hideTab("dynamic_tab4");
    }
}
