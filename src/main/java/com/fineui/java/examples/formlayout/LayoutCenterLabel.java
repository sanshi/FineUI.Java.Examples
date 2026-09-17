package com.fineui.java.examples.formlayout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Form;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 表单布局——标题标签居中（路由 {@code form-layout/layout-center-label}）：表单内嵌标题标签 + 底部居中工具栏按钮。 */
@FineUIPage("form-layout/layout-center-label")
public class LayoutCenterLabel extends PageBase {

    protected Form Form1;
    protected Button btnSubmit;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
