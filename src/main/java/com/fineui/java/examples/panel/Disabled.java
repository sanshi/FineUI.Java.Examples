package com.fineui.java.examples.panel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Panel;
import com.fineui.java.examples.code.PageBase;

/**
 * 禁用面板演示页（路由 {@code panel/disabled}）：整块面板置为禁用（灰显、内部控件不可交互），
 * 底部工具栏演示禁用态下的文本/分隔/按钮，页面底部按钮在服务端切换面板的启用/禁用。
 */
@FineUIPage("panel/disabled")
public class Disabled extends PageBase {

    Panel Panel1;
    Panel Panel2;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Panel2.setContent("可以在此放置<a href=\"http://www.w3schools.com/html/\" target=\"_blank\">HTML</a>标签。");
    }

    /** 切换面板的启用/禁用状态。 */
    public void Button2_Click(Object sender, EventArgs e) {
        Panel1.setEnabled(!Panel1.isEnabled());
    }
}
