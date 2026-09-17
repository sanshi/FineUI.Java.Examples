package com.fineui.java.examples.form;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TwinTriggerBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 双触发按钮文本框·清除图标演示页（路由 {@code form/twin-trigger-box-clear-icon}）：有内容时显示清除图标；
 * 输入停顿后值变化触发自定义事件回发，服务端按当前值有无分派到搜索 / 取消搜索。
 */
@FineUIPage("form/twin-trigger-box-clear-icon")
public class TwinTriggerBoxClearIcon extends PageBase {

    TwinTriggerBox TwinTriggerBox1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("TwinTriggerBox1_Change".equals(e.getEventName())) {
            String text = TwinTriggerBox1.getValue();
            if (text != null && !text.isEmpty()) {
                TwinTriggerBox1_Trigger2Click(null, null);
            } else {
                TwinTriggerBox1_Trigger1Click(null, null);
            }
        }
    }

    public void TwinTriggerBox1_Trigger2Click(Object sender, EventArgs e) {
        String text = TwinTriggerBox1.getValue();
        if (text != null && !text.isEmpty()) {
            showNotify("在关键词“" + text + "”中搜索");
            updateClientJsParameter(text);
        } else {
            showNotify("请输入你要搜索的关键词！");
        }
    }

    public void TwinTriggerBox1_Trigger1Click(Object sender, EventArgs e) {
        showNotify("取消搜索！");
        updateClientJsParameter("");
    }

    /** 把服务端认定的当前值回写客户端 __lastTriggerBoxValue，避免下次 blur/change 重复回发。 */
    private void updateClientJsParameter(String text) {
        invokeClientFunction("updateLastTriggerBoxValue", text);
    }
}
