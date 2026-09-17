package com.fineui.java.examples.formlayout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Form;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 表单布局——按钮居中（路由 {@code form-layout/layout-center-button}）：用 HBox 面板将提交/重置按钮水平居中。 */
@FineUIPage("form-layout/layout-center-button")
public class LayoutCenterButton extends PageBase {

    protected Form Form1;
    protected Button btnSubmit;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
