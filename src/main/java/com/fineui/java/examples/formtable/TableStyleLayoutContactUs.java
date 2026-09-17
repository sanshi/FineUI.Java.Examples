package com.fineui.java.examples.formtable;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Form;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 表格式联系我们（路由 {@code form-table/table-style-layout-contact-us}）：窗体内表格式联系表单。 */
@FineUIPage("form-table/table-style-layout-contact-us")
public class TableStyleLayoutContactUs extends PageBase {

    protected Form Form1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
