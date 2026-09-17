package com.fineui.java.examples.accordion;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 手风琴（自动高度）页（路由 {@code accordion/fill}）：关闭面板填充（{@code enable-fill="false"}）后，
 * 面板高度由内容决定、可全部折叠；按钮读取当前展开序号（全部折叠时为 -1）、展开下一个面板。
 */
@FineUIPage("accordion/fill")
public class Fill extends PageBase {

    com.fineui.java.core.controls.Accordion Accordion1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 提示当前展开的面板序号；全部折叠时提示无展开面板。 */
    public void Button1_Click(Object sender, EventArgs e) {
        if (Accordion1.getActivePaneIndex() == -1) {
            showNotify("当前没有面板处于展开状态！");
        } else {
            showNotify("当前展开的是第 " + (Accordion1.getActivePaneIndex() + 1) + " 个面板");
        }
    }

    /** 展开下一个面板（到末尾后回到第一个）。 */
    public void Button2_Click(Object sender, EventArgs e) {
        int nextIndex = Accordion1.getActivePaneIndex() + 1;
        if (nextIndex >= 3) {
            nextIndex = 0;
        }
        Accordion1.setActivePaneIndex(nextIndex);
    }
}
