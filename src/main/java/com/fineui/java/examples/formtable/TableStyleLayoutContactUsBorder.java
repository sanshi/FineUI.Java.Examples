package com.fineui.java.examples.formtable;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Form;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 带边框的表格式联系我们（路由 {@code form-table/table-style-layout-contact-us-border}）：窗体 BodyPadding=0，表格式表单边框贴边。 */
@FineUIPage("form-table/table-style-layout-contact-us-border")
public class TableStyleLayoutContactUsBorder extends PageBase {

    protected Form Form1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
