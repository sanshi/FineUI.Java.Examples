package com.fineui.java.examples.accordion;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 手风琴（面板改变事件）页（路由 {@code accordion/pane-index-changed}）：切换展开面板时触发服务端
 * {@code on-pane-index-changed} 事件（客户端 {@code panechange}），服务端提示当前展开序号。
 */
@FineUIPage("accordion/pane-index-changed")
public class PaneIndexChanged extends PageBase {

    com.fineui.java.core.controls.Accordion Accordion1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 提示当前展开的面板序号。 */
    public void Button1_Click(Object sender, EventArgs e) {
        showNotify("当前展开的是第 " + (Accordion1.getActivePaneIndex() + 1) + " 个面板");
    }

    /** 展开面板切换事件：提示当前展开序号。 */
    public void Accordion1_PaneIndexChanged(Object sender, EventArgs e) {
        showNotify("当前展开的是第 " + (Accordion1.getActivePaneIndex() + 1) + " 个面板（手风琴面板改变事件）");
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
