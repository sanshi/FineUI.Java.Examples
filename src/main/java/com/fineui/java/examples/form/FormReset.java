package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单重置演示页（路由 {@code form/form-reset}）：客户端 {@code F.ui.表单.reset()}
 * 把表单字段恢复到初始值；两个表单可分别或一起重置、校验提交。
 */
@FineUIPage("form/form-reset")
public class FormReset extends PageBase {

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

    public void btnSubmitAll_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1, Form2));
    }
}
