package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单文本对齐演示页（路由 {@code form/form-text-align}）：通过 css-class
 * （text-align-center / text-align-right）控制输入框内文本的对齐方式。
 */
@FineUIPage("form/form-text-align")
public class FormTextAlign extends PageBase {

    protected SimpleForm SimpleForm1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(SimpleForm1));
    }
}
