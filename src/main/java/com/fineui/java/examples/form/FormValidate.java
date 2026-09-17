package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单服务端校验演示页（路由 {@code form/form-validate}）：提交时若用户名为保留字 admin，
 * 服务端把该字段标记为无效；否则弹出提交结果。
 */
@FineUIPage("form/form-validate")
public class FormValidate extends PageBase {

    protected TextBox tbxUserName;
    protected TextBox tbxPassword;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnRegister_Click(Object sender, EventArgs e) {
        if ("admin".equals(tbxUserName.getValue())) {
            tbxUserName.markInvalid(tbxUserName.getValue() + " 是保留字，请另外选择！");
        } else {
            showNotify("用户名：" + tbxUserName.getValue() + " 密码：" + tbxPassword.getValue());
        }
    }
}
