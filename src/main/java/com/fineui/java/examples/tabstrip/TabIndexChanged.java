package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 选项卡改变事件（路由 {@code tab-strip/tab-index-changed}）：TabStrip 激活的选项卡切换时触发服务端
 * {@code on-tab-index-changed} 事件，在对应选项卡内的标签上写入回发时间；另有按钮循环切换到下一个选项卡。
 */
@FineUIPage("tab-strip/tab-index-changed")
public class TabIndexChanged extends PageBase {

    com.fineui.java.core.controls.TabStrip TabStrip1;
    Label Label1;
    Label Label2;
    Label Label3;

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 切换到下一个选项卡（循环）。 */
    public void Button3_Click(Object sender, EventArgs e) {
        int nextIndex = TabStrip1.getActiveTabIndex() + 1;

        if (nextIndex >= 3) {
            nextIndex = 0;
        }

        TabStrip1.setActiveTabIndex(nextIndex);
    }

    /** 激活的选项卡改变：在当前选项卡内的标签上写入回发时间。 */
    public void TabStrip1_TabIndexChanged(Object sender, EventArgs e) {
        String now = "标签回发时间：" + LocalTime.now().format(TIME_FORMAT);
        if (TabStrip1.getActiveTabIndex() == 0) {
            Label1.setText(now);
        } else if (TabStrip1.getActiveTabIndex() == 1) {
            Label2.setText(now);
        } else if (TabStrip1.getActiveTabIndex() == 2) {
            Label3.setText(now);
        }
    }
}
