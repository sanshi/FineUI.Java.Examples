package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.core.controls.TriggerBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 标签很长的表单（路由 {@code form/form-long-label}）：各类表单字段使用超长标签的渲染效果，
 * 全部为必填项（提交按钮带 {@code validate-forms} 校验）。
 */
@FineUIPage("form/form-long-label")
public class FormLongLabel extends PageBase {

    TextBox tbxUserName;
    TriggerBox tbxMyBox1;
    NumberBox NumberBox5;
    DatePicker DatePicker1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofFields(tbxUserName, tbxMyBox1, NumberBox5, DatePicker1));
    }
}
