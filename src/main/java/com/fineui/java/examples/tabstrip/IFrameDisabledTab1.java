package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * IFrame 内页（路由 {@code tab-strip/iframe-disabled-tab1}）：供 {@code tab-strip/iframe-disabled} 的第一个
 * 选项卡内嵌加载；两个按钮通过 {@code parent.enableTabs()} / {@code parent.disableTabs()} 控制父页面后面的选项卡。
 */
@FineUIPage("tab-strip/iframe-disabled-tab1")
public class IFrameDisabledTab1 extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
