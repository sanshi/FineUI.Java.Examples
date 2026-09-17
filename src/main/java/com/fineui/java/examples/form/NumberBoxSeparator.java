package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字输入框·自定义分隔符演示页（路由 {@code form/number-box-separator}）：CommaSeparator 自定义千分位分隔符
 * （单引号 {@code '}、空格），重新输入失焦后按自定义分隔符重新格式化。提交时服务端读回各框的值并弹通知。
 */
@FineUIPage("form/number-box-separator")
public class NumberBoxSeparator extends PageBase {

    NumberBox NumberBox1;
    NumberBox NumberBox2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofFields(NumberBox1, NumberBox2));
    }
}
