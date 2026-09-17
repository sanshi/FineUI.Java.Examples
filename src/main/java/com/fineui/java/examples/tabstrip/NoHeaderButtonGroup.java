package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 隐藏选项卡标题栏（配合按钮分组）（路由 {@code tab-strip/no-header-button-group}）：左侧竖排按钮分组，
 * 右侧隐藏标题栏的选项卡组；点击按钮在客户端激活对应选项卡（presschange 客户端监听）。
 */
@FineUIPage("tab-strip/no-header-button-group")
public class NoHeaderButtonGroup extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
