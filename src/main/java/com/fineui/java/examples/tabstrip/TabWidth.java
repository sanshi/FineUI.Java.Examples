package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 限制标题文字宽度（路由 {@code tab-strip/tab-width}）：通过页面自定义 CSS 类
 * {@code tab-maxwidth} 给选项卡标题设置 {@code max-width}，超长标题被截断，并用
 * {@code title-tool-tip} 显示完整文字。
 */
@FineUIPage("tab-strip/tab-width")
public class TabWidth extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
