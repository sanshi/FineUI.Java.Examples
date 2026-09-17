package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * IFrame 内启用外部选项卡（路由 {@code tab-strip/iframe-disabled}）：第一个选项卡的 IFrame 内页
 * 提供两个按钮，通过 {@code parent.enableTabs()} / {@code parent.disableTabs()} 启用或禁用后面两个选项卡。
 */
@FineUIPage("tab-strip/iframe-disabled")
public class IFrameDisabled extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
