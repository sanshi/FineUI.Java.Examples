package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 隐藏全部选项卡（路由 {@code tab-strip/no-tabs}）：三个选项卡初始都隐藏（hidden=true），
 * 靠页面按钮在客户端显示、显示并激活、隐藏对应选项卡。
 */
@FineUIPage("tab-strip/no-tabs")
public class NoTabs extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
