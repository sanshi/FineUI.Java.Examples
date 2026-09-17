package com.fineui.java.examples.mobile.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.mobile.MobilePageBase;

/**
 * 移动端系统登录（自定义错误提示）演示页（路由 {@code mobile/form/login-custom-error-message}）：登录按钮客户端脚本
 * 手动校验表单并用自定义提示框列出错误字段；校验通过后回发（仅校验密码），居中通知登录结果。
 */
@FineUIPage("mobile/form/login-custom-error-message")
public class LoginCustomErrorMessage extends MobilePageBase {

    TextBox tbxUserName;
    TextBox tbxPassword;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnLogin_Click(Object sender, EventArgs e) {
        if ("admin".equals(tbxPassword.getValue())) {
            showCenterNotify("成功登录！", MessageBoxIcon.Success);
        } else {
            showCenterNotify("用户名或密码错误！", MessageBoxIcon.Error);
        }
    }
}
