package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 文本框服务端事件演示页（路由 {@code form/text-box-text-changed}）：
 * 文本框一绑定「文本改变」事件（值改变确认时触发），文本框二绑定「失去焦点」事件（失焦时触发）；
 * 触发即回发，服务端读回当前输入值并回填到对应结果标签——无整页刷新、纯 JSON 回发。
 */
@FineUIPage("form/text-box-text-changed")
public class TextBoxTextChanged extends PageBase {

    // 字段名与控件 id 同名以完成反射绑定；控件类型与页面类同名无冲突，仍用短名（这里页面类是 TextBoxTextChanged）。
    com.fineui.java.core.controls.TextBox TextBox1;
    com.fineui.java.core.controls.TextBox TextBox2;
    Label labResult1;
    Label labResult2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void TextBox1_TextChanged(Object sender, EventArgs e) {
        labResult1.setText("文本框一：" + TextBox1.getValue());
    }

    public void TextBox2_Blur(Object sender, EventArgs e) {
        labResult2.setText("文本框二：" + TextBox2.getValue());
    }
}
