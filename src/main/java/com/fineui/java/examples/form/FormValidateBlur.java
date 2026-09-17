package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单失焦校验演示页（路由 {@code form/form-validate-blur}）：用户名失去焦点时触发服务端校验，
 * 为保留字 admin 则标记无效，否则清除无效标记并聚焦到下一个字段。
 */
@FineUIPage("form/form-validate-blur")
public class FormValidateBlur extends PageBase {

    protected TextBox tbxUserName;
    protected TextBox tbxPassword;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnRegister_Click(Object sender, EventArgs e) {
        if (validateForm(tbxUserName.getValue())) {
            showNotify("用户名：" + tbxUserName.getValue() + " 密码：" + tbxPassword.getValue());
        }
    }

    public void tbxUserName_Blur(Object sender, EventArgs e) {
        if (validateForm(tbxUserName.getValue())) {
            tbxPassword.focus();
        }
    }

    private boolean validateForm(String userName) {
        if ("admin".equals(userName)) {
            tbxUserName.markInvalid(userName + " 是保留字，请另外选择！");
            return false;
        }
        tbxUserName.clearInvalid();
        return true;
    }
}
