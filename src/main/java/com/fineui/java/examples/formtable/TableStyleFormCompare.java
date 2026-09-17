package com.fineui.java.examples.formtable;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 表格式表单比较校验示例（路由 {@code form-table/table-style-form-compare}）：多组表格式子表单，字段间比较校验。 */
@FineUIPage("form-table/table-style-form-compare")
public class TableStyleFormCompare extends PageBase {

    protected SimpleForm SimpleForm1;
    protected Button btnSubmit;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(SimpleForm1));
    }
}
