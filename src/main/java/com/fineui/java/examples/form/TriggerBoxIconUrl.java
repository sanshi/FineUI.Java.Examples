package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TriggerBox;
import com.fineui.java.core.controls.TwinTriggerBox;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义触发图标（路由 {@code form/trigger-box-icon-url}）：TriggerBox / TwinTriggerBox 用
 * 自定义图片作为触发图标（背景图样式类），触发点击行为与 TwinTriggerBox 的搜索 / 取消流程不变。
 */
@FineUIPage("form/trigger-box-icon-url")
public class TriggerBoxIconUrl extends PageBase {

    TriggerBox TriggerBox1;
    TwinTriggerBox TwinTriggerBox1;
    Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnCloseWindow_Click(Object sender, EventArgs e) {
        Window1.setHidden(true);
        TriggerBox1.setValue("弹出窗口被关闭了");
    }

    public void TwinTriggerBox1_Trigger2Click(Object sender, EventArgs e) {
        // 点击 TwinTriggerBox 的搜索按钮
        if (!TwinTriggerBox1.getValue().isEmpty()) {
            // 执行搜索动作
            showNotify("在关键词“" + TwinTriggerBox1.getValue() + "”中搜索");
            TwinTriggerBox1.setShowTrigger1(true);
        } else {
            showNotify("请输入你要搜索的关键词！");
        }
    }

    public void TwinTriggerBox1_Trigger1Click(Object sender, EventArgs e) {
        // 点击 TwinTriggerBox 的取消按钮
        showNotify("取消搜索！");
        // 执行清空动作
        TwinTriggerBox1.setValue("");
        TwinTriggerBox1.setShowTrigger1(false);
    }
}
