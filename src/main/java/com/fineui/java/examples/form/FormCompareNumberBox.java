package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单比较校验（数字框）演示页（路由 {@code form/form-compare-number-box}）：数字框 2 需大于等于数字框 1、
 * 数字框 4 需大于等于数字框 3（比较校验）；必填对须填写、可空对可留空。客户端校验通过后才提交、服务端汇总字段值弹通知。
 * 由真实 F.js 渲染、纯 JSON 回发。
 */
@FineUIPage("form/form-compare-number-box")
public class FormCompareNumberBox extends PageBase {

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
