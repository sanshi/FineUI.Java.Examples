package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单演示页（路由 {@code form/form}）：两个表单各含多个表单行（一行横排多个字段），
 * 按钮通过 validate-forms 校验指定表单，提交后弹出该表单的字段值汇总。
 */
@FineUIPage("form/form")
public class Form extends PageBase {

    // 字段类型用全限定名，避免与本示例页类名 Form 冲突。
    protected com.fineui.java.core.controls.Form Form1;
    protected com.fineui.java.core.controls.Form Form2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmitForm1_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }

    public void btnSubmitForm2_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form2));
    }

    public void btnSubmitForm_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1, Form2));
    }
}
