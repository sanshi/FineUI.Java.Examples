package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.examples.code.PageBase;

/**
 * 按值校验演示页（路由 {@code form/validate-for-value}）：默认按显示文本长度校验 MinLength，
 * validate-for-value=true 时改按选中值（Value）的长度校验。
 */
@FineUIPage("form/validate-for-value")
public class ValidateForValue extends PageBase {

    protected SimpleForm SimpleForm1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmitForm_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(SimpleForm1));
    }
}
