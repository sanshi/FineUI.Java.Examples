package com.fineui.java.examples.formtable;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Form;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 表格式表单示例（路由 {@code form-table/table-style-form}）：两个表格式表单（含标签/复选框/下拉/文本/邮箱正则校验），可分别或一起验证提交。 */
@FineUIPage("form-table/table-style-form")
public class TableStyleForm extends PageBase {

    protected Form Form1;
    protected Form Form2;
    protected Button btnSubmitForm1;
    protected Button btnSubmitForm2;
    protected Button btnSubmitAll;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmitForm1_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }

    public void btnSubmitForm2_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form2));
    }

    public void btnSubmitAll_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1, Form2));
    }
}
