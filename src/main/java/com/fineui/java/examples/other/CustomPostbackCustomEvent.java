package com.fineui.java.examples.other;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义事件演示页面模型类（路由 {@code other/custom-postback-custom-event}）：Form ▸ 2×TextBox，
 * TextBox1 挂 {@code enter} 监听，客户端函数 {@code onTextBoxEnter} 调
 * {@code F.customEvent('TextBox1_ENTER')} 触发后台自定义事件。
 *
 * <p>后台统一入口 {@code Page_CustomEvent} 按事件名分派：{@code TextBox1_ENTER} 时把 TextBox1 的值
 * 同步到 TextBox2。全程无整页刷新、纯 JSON 回发。
 */
@FineUIPage("other/custom-postback-custom-event")
public class CustomPostbackCustomEvent extends PageBase {

    TextBox TextBox1;
    TextBox TextBox2;

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("TextBox1_ENTER".equals(e.getEventName())) {
            TextBox2.setValue(TextBox1.getValue());
            TextBox2.focus();
        }
    }
}
