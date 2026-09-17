package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字输入框·文本改变事件演示页（路由 {@code form/number-box-text-changed}）：手工输入数字、或点击右侧上下箭头
 * 改变数值都会触发服务端 OnTextChanged 事件并弹通知；另有提交按钮读回当前值。
 */
@FineUIPage("form/number-box-text-changed")
public class NumberBoxTextChanged extends PageBase {

    NumberBox NumberBox3;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void NumberBox3_TextChanged(Object sender, EventArgs e) {
        showNotify("数字输入框的值（NumberBox3_TextChanged）：" + NumberBox3.getValue());
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotify("数字输入框的值（btnSubmit_Click）：" + NumberBox3.getValue());
    }
}
