package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字输入框·千分位演示页（路由 {@code form/number-box-commas}）：开启 EnableCommas 后，数字以千分位逗号显示，
 * 并保持精度/四舍五入；重新输入失焦后再次格式化。提交时服务端读回各框的值并弹通知。
 */
@FineUIPage("form/number-box-commas")
public class NumberBoxCommas extends PageBase {

    NumberBox NumberBox1;
    NumberBox NumberBox2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofFields(NumberBox1, NumberBox2));
    }
}
