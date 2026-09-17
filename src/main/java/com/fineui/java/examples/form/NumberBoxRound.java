package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字输入框·四舍五入演示页（路由 {@code form/number-box-round}）：默认按精度四舍五入（1.2367→1.24）；
 * EnableRound=false 时直接截断不进位（1.2367→1.23）。提交时服务端读回各框的值并弹通知。
 */
@FineUIPage("form/number-box-round")
public class NumberBoxRound extends PageBase {

    NumberBox NumberBox1;
    NumberBox NumberBox2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofFields(NumberBox1, NumberBox2));
    }
}
