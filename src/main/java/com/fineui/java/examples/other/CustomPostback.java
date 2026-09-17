package com.fineui.java.examples.other;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义回发（F.doPostBack）演示页面模型类（路由 {@code other/custom-postback}）：TextBox1 按回车触发
 * {@code F.doPostBack({ eventName: 'TextBox1_ENTER' })}，服务端在自定义事件
 * {@code TextBox1_ENTER} 里把 TextBox1 的值回写到 TextBox2。
 */
@FineUIPage("other/custom-postback")
public class CustomPostback extends PageBase {

    TextBox TextBox1;
    TextBox TextBox2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("TextBox1_ENTER".equals(e.getEventName())) {
            TextBox2.setValue(TextBox1.getValue());
            TextBox2.focus();
        }
    }
}
