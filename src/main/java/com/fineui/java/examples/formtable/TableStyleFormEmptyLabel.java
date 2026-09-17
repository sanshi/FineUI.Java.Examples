package com.fineui.java.examples.formtable;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Form;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 表格式表单空标签占位示例（路由 {@code form-table/table-style-form-empty-label}）：用空 Label 在双列布局中占位对齐。 */
@FineUIPage("form-table/table-style-form-empty-label")
public class TableStyleFormEmptyLabel extends PageBase {

    protected Form Form1;
    protected Form Form2;
    protected Button btnSubmitForm1;
    protected Button btnSubmitForm2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmitForm1_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }

    public void btnSubmitForm2_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form2));
    }
}
