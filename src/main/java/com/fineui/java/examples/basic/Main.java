package com.fineui.java.examples.basic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 首页框架的默认工作区落地页（路由 {@code basic/main}）：首页的“首页”选项卡以 IFrame 加载本页。
 * 纯欢迎页，无控件、无服务端逻辑。
 */
@FineUIPage("basic/main")
public class Main extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
