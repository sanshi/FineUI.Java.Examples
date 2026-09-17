package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字输入框·小数精度演示页（路由 {@code form/number-box-decimal-precision}）：工具栏按钮在运行时修改小数精度与步长，
 * 数字框即时按新精度重新格式化（TrimEndZero=false，故保留末尾的零，如精度 3 时显示 0.360）。提交时弹出当前值。
 */
@FineUIPage("form/number-box-decimal-precision")
public class NumberBoxDecimalPrecision extends PageBase {

    NumberBox NumberBox1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotify("数字输入框的值：" + NumberBox1.getValue());
    }

    public void Button1_Click(Object sender, EventArgs e) {
        NumberBox1.setDecimalPrecision(1);
        NumberBox1.setIncrement(0.1);
    }

    public void Button2_Click(Object sender, EventArgs e) {
        NumberBox1.setDecimalPrecision(2);
        NumberBox1.setIncrement(0.01);
    }

    public void Button3_Click(Object sender, EventArgs e) {
        NumberBox1.setDecimalPrecision(3);
        NumberBox1.setIncrement(0.001);
    }
}
