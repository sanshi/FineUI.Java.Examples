package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字框自定义触发图标演示页（路由 {@code form/number-box-trigger-icon}）：在不同 TriggerType 布局上，
 * 用自定义 CSS 类（{@code custom-trigger-icon}/{@code custom-trigger-icon2}）覆写上下调整箭头的图标字符。
 * 由真实 F.js 渲染、纯 JSON 回发。
 */
@FineUIPage("form/number-box-trigger-icon")
public class NumberBoxTriggerIcon extends PageBase {

    NumberBox NumberBox1;
    NumberBox NumberBox2;
    NumberBox NumberBox3;
    NumberBox NumberBox4;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofFields(NumberBox1, NumberBox2, NumberBox3, NumberBox4));
    }
}
