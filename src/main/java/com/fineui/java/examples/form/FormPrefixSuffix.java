package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.examples.code.PageBase;

/** 字段前缀/后缀（路由 {@code form/form-prefix-suffix}）：在输入框前后显示固定文本，提交后弹出字段值汇总。 */
@FineUIPage("form/form-prefix-suffix")
public class FormPrefixSuffix extends PageBase {

    protected SimpleForm SimpleForm1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(SimpleForm1));
    }
}
