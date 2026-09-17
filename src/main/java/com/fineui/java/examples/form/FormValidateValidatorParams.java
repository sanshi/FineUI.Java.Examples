package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.examples.code.PageBase;

/**
 * 参数化自定义校验演示页（路由 {@code form/form-validate-validator-params}）：同一校验函数通过
 * 自定义属性 data-maxlength 读取参数，两个输入框各自限制不同的最大长度。
 */
@FineUIPage("form/form-validate-validator-params")
public class FormValidateValidatorParams extends PageBase {

    protected SimpleForm SimpleForm1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnRegister_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(SimpleForm1));
    }
}
