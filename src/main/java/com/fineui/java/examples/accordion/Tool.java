package com.fineui.java.examples.accordion;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 手风琴（工具图标）页（路由 {@code accordion/tool}）：面板一的标题栏放两个工具图标（设置/下载），
 * 点击工具图标弹通知并阻止折叠面板（客户端 {@code event.stopPropagation()}）。
 */
@FineUIPage("accordion/tool")
public class Tool extends PageBase {

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
