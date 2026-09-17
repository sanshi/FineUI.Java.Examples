package com.fineui.java.examples.accordion;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 手风琴综合演示页（路由 {@code accordion/accordion}）：三个手风琴面板，配两个按钮——
 * 获取当前展开面板序号、展开下一个面板。
 */
@FineUIPage("accordion/accordion")
public class Accordion extends PageBase {

    com.fineui.java.core.controls.Accordion Accordion1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 提示当前展开的面板序号。 */
    public void Button1_Click(Object sender, EventArgs e) {
        showNotify("当前展开的是第 " + (Accordion1.getActivePaneIndex() + 1) + " 个面板");
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
