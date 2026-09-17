package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字框去尾零演示页（路由 {@code form/number-box-trim-end-zero}）：TrimEndZero=true 时去掉小数末尾的零
 * （{@code 1.20}→{@code 1.2}）、false 时保留（{@code 1.20}）。由真实 F.js 渲染、纯 JSON 回发。
 */
@FineUIPage("form/number-box-trim-end-zero")
public class NumberBoxTrimEndZero extends PageBase {

    NumberBox NumberBox1;
    NumberBox NumberBox2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofFields(NumberBox1, NumberBox2));
    }
}
