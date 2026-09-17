package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 后台添加更新 IFrame 选项卡（路由 {@code tab-strip/iframe-add-tab}）：三个按钮在服务端动态添加
 * IFrame 选项卡；因指定了相同的 tabID，重复点击会在同一个选项卡中打开新网址（更新而非新增）。
 */
@FineUIPage("tab-strip/iframe-add-tab")
public class IFrameAddTab extends PageBase {

    com.fineui.java.core.controls.TabStrip TabStrip1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 在 tab1_iframe 中打开 DeepSeek 官网。 */
    public void Button1_Click(Object sender, EventArgs e) {
        TabStrip1.addTab("tab1_iframe", "https://deepseek.com/", "DeepSeek官网", true);
    }

    /** 在 tab1_iframe 中打开 Spring 官网。 */
    public void Button2_Click(Object sender, EventArgs e) {
        TabStrip1.addTab("tab1_iframe", "https://spring.io/", "Spring 官网", true);
    }

    /** 在 tab2_iframe 中打开 FineUI 官网。 */
    public void Button3_Click(Object sender, EventArgs e) {
        TabStrip1.addTab("tab2_iframe", "https://fineui.com/", "FineUI官网", true);
    }
}
