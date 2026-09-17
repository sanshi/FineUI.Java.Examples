package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 双触发按钮文本框演示页（路由 {@code form/twin-trigger-box}）：左侧清除按钮（初始隐藏）、右侧搜索按钮；
 * 点击搜索在关键词中搜索、点击清除取消搜索。
 */
@FineUIPage("form/twin-trigger-box")
public class TwinTriggerBox extends PageBase {

    com.fineui.java.core.controls.TwinTriggerBox TwinTriggerBox1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void TwinTriggerBox1_Trigger2Click(Object sender, EventArgs e) {
        String text = TwinTriggerBox1.getValue();
        if (text != null && !text.isEmpty()) {
            showNotify("在关键词“" + text + "”中搜索");
            TwinTriggerBox1.setShowTrigger1(true);
        } else {
            showNotify("请输入你要搜索的关键词！");
        }
    }

    public void TwinTriggerBox1_Trigger1Click(Object sender, EventArgs e) {
        showNotify("取消搜索！");
        TwinTriggerBox1.setValue("");
        TwinTriggerBox1.setShowTrigger1(false);
    }
}
