package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字输入框演示页（路由 {@code form/number-box}）：几个带取值范围/整数/负数/小数精度/步长约束的数字框——
 * 客户端即时校验（超范围、必填），上下箭头与方向键按步长增减；提交时服务端读回各框的值并弹通知。
 * 由真实 F.js 渲染、纯 JSON 回发。
 */
@FineUIPage("form/number-box")
public class NumberBox extends PageBase {

    // 页面类名 NumberBox 与控件类同名，控件字段用全限定名消歧。
    com.fineui.java.core.controls.NumberBox NumberBox1;
    com.fineui.java.core.controls.NumberBox NumberBox3;
    com.fineui.java.core.controls.NumberBox NumberBox4;
    com.fineui.java.core.controls.NumberBox NumberBox5;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofFields(NumberBox1, NumberBox3, NumberBox4, NumberBox5));
    }
}
