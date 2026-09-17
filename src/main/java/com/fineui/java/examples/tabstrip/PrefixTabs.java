package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 按钮放在选项卡前面（路由 {@code tab-strip/prefix-tabs}）：把一个下拉按钮浮动到选项卡标题栏左侧（客户端脚本布局），
 * 点击菜单项在服务端往选项卡组新增一个 IFrame 选项卡（相同 id 会复用同一个选项卡、只换地址）。
 */
@FineUIPage("tab-strip/prefix-tabs")
public class PrefixTabs extends PageBase {

    com.fineui.java.core.controls.TabStrip TabStrip1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 打开 DeepSeek 官网（复用 id 为 tab1_iframe 的选项卡）。 */
    public void Button1_Click(Object sender, EventArgs e) {
        TabStrip1.addTab("tab1_iframe", "https://deepseek.com/", "DeepSeek官网", true);
    }

    /** 打开 Spring 官网（复用 id 为 tab1_iframe 的选项卡）。 */
    public void Button2_Click(Object sender, EventArgs e) {
        TabStrip1.addTab("tab1_iframe", "https://spring.io/", "Spring 官网", true);
    }

    /** 打开 FineUI 官网（复用 id 为 tab2_iframe 的选项卡）。 */
    public void Button3_Click(Object sender, EventArgs e) {
        TabStrip1.addTab("tab2_iframe", "https://fineui.com/", "FineUI官网", true);
    }
}
