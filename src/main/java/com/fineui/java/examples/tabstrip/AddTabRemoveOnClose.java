package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.IconHelper;
import com.fineui.java.examples.code.PageBase;

/**
 * 关闭时移除实例（路由 {@code tab-strip/add-tab-remove-on-close}）：动态添加的 IFrame 选项卡在关闭时连同其
 * 实例一并移除（{@code removeOnClose}）。客户端按钮直接调 {@code F.ui.TabStrip1.addTab({...removeOnClose:true})}
 * / {@code closeTab}；服务端按钮经运行时命令下发。
 */
@FineUIPage("tab-strip/add-tab-remove-on-close")
public class AddTabRemoveOnClose extends PageBase {

    com.fineui.java.core.controls.TabStrip TabStrip1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 服务端添加 DeepSeek 官网选项卡（关闭时移除实例）。 */
    public void btnAddTab3_Click(Object sender, EventArgs e) {
        TabStrip1.addTab("dynamic_tab3", "https://deepseek.com/", "DeepSeek官网（服务端代码）",
                IconHelper.namedIconUrl("Application"), true, true);
    }

    /** 服务端添加 Spring 官网选项卡（关闭时移除实例）。 */
    public void btnAddTab4_Click(Object sender, EventArgs e) {
        TabStrip1.addTab("dynamic_tab4", "https://spring.io/", "Spring 官网（服务端代码）",
                IconHelper.namedIconUrl("ApplicationAdd"), true, true);
    }

    /** 服务端关闭 DeepSeek 官网选项卡（removeOnClose 时移除实例）。 */
    public void btnRemoveTab3_Click(Object sender, EventArgs e) {
        TabStrip1.closeTab("dynamic_tab3");
    }

    /** 服务端关闭 Spring 官网选项卡（removeOnClose 时移除实例）。 */
    public void btnRemoveTab4_Click(Object sender, EventArgs e) {
        TabStrip1.closeTab("dynamic_tab4");
    }
}
