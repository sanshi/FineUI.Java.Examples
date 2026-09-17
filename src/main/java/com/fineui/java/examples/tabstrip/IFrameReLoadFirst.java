package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 刷新第一个选项卡（路由 {@code tab-strip/iframe-reload-first}）：三个 IFrame 选项卡，只有第一个在每次被
 * 激活时（客户端 {@code tabchange} 事件里调 {@code tab.refreshIFrame()}）重新加载其 IFrame，其余保持缓存。
 */
@FineUIPage("tab-strip/iframe-reload-first")
public class IFrameReLoadFirst extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
