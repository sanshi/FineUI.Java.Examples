package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字输入框·前后缀演示页（路由 {@code form/number-box-prefix}）：NumberPrefix / NumberSuffix 给数字加上货币符号
 * （￥、$）前缀或百分号（%）后缀，与千分位/精度共存。提交时服务端读回各框的值并弹通知。
 */
@FineUIPage("form/number-box-prefix")
public class NumberBoxPrefix extends PageBase {

    NumberBox NumberBox1;
    NumberBox NumberBox2;
    NumberBox NumberBox3;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofFields(NumberBox1, NumberBox2, NumberBox3));
    }
}
