package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单隐藏字段演示页（路由 {@code form/form-hide-field}）：对比两种隐藏方式——
 * Visibility（隐藏后仍占位）与默认 Display（隐藏后不占位），可对单个字段或整行生效。
 */
@FineUIPage("form/form-hide-field")
public class FormHideField extends PageBase {

    protected com.fineui.java.core.controls.Form Form1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
