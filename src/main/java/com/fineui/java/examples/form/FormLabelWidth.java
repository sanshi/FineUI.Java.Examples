package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单标签宽度演示页（路由 {@code form/form-label-width}）：字段自身定义的 label-width / label-align
 * 会覆盖表单级的同名默认（表单 label-width=120，Label1/DropDownList1 覆盖为 150）。
 */
@FineUIPage("form/form-label-width")
public class FormLabelWidth extends PageBase {

    protected com.fineui.java.core.controls.Form Form1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmitForm1_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
