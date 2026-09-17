package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 选项卡基本用法（路由 {@code tab-strip/tab-strip}）：三个选项卡分别放表单、按钮、标签；
 * 按钮点击弹通知、验证第一个选项卡里的表单、并在服务端切换到下一个选项卡。
 */
@FineUIPage("tab-strip/tab-strip")
public class TabStrip extends PageBase {

    com.fineui.java.core.controls.TabStrip TabStrip1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 第二个选项卡里的按钮点击：弹通知。 */
    public void Button1_Click(Object sender, EventArgs e) {
        showNotify("你点击了位于第二个标签中的一个按钮！");
    }

    /** 切换到下一个选项卡（循环）。 */
    public void Button3_Click(Object sender, EventArgs e) {
        int nextIndex = TabStrip1.getActiveTabIndex() + 1;
        if (nextIndex >= 3) {
            nextIndex = 0;
        }
        TabStrip1.setActiveTabIndex(nextIndex);
    }
}
