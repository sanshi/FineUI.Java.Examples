package com.fineui.java.examples.formlayout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Form;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 表单布局——列布局面板（路由 {@code form-layout/layout-panel-column}）：用 Column 布局面板把标签/字段/按钮横向排列成一行。 */
@FineUIPage("form-layout/layout-panel-column")
public class LayoutPanelColumn extends PageBase {

    protected Form Form1;
    protected Button Button1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
