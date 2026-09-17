package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 多窗体演示页（路由 {@code window/multi}）：页面预置三个各含表单的窗体，
 * 顶部下拉列表选择要显示的窗体，点击按钮后由客户端脚本按下拉值显示对应窗体。
 */
@FineUIPage("window/multi")
public class Multi extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
