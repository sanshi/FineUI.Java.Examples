package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 关闭前弹确认框（路由 {@code tab-strip/beforehide}）：标签三监听客户端 {@code beforehide} 事件，
 * 关闭前弹出浏览器确认框，取消则返回 false 阻止关闭。
 */
@FineUIPage("tab-strip/beforehide")
public class Beforehide extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
