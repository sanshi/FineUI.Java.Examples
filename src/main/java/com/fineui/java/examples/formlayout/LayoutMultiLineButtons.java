package com.fineui.java.examples.formlayout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 表单布局——多行按钮（路由 {@code form-layout/layout-multi-line-buttons}）：用无边框面板承载多行按钮。 */
@FineUIPage("form-layout/layout-multi-line-buttons")
public class LayoutMultiLineButtons extends PageBase {

    protected SimpleForm Form1;
    protected Button btnSubmit;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
