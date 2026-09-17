package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单自定义校验演示页（路由 {@code form/form-validate-validator}）：通过 validator-function
 * 指定客户端校验函数，[密码]必须为 6 个字符，校验通过后提交。
 */
@FineUIPage("form/form-validate-validator")
public class FormValidateValidator extends PageBase {

    protected TextBox tbxUserName;
    protected TextBox tbxPassword;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnRegister_Click(Object sender, EventArgs e) {
        showNotify("用户名：" + tbxUserName.getValue() + " 密码：" + tbxPassword.getValue());
    }
}
