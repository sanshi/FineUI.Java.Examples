package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单列宽演示页（路由 {@code form/form-column-widths}）：表单行通过 column-widths
 * 指定各列宽度（像素与百分比可混用，如 {@code "20px 50% 50%"}），首列固定 20px 放序号标签。
 */
@FineUIPage("form/form-column-widths")
public class FormColumnWidths extends PageBase {

    protected com.fineui.java.core.controls.Form Form1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
