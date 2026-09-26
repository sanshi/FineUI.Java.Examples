package com.fineui.java.examples.basic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 登录回车键（路由 {@code basic/login-enter-key}）：表单内按回车键用
 * {@code next-focus-control} 把焦点从用户名框导航到密码框，
 * {@code next-click-control} 在密码框按回车时点击登录按钮并回发校验。
 */
@FineUIPage("basic/login-enter-key")
public class LoginEnterKey extends PageBase {

    TextBox tbxUserName;
    TextBox tbxPassword;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnLogin_Click(Object sender, EventArgs e) {
        if ("admin".equals(tbxUserName.getValue()) && "admin".equals(tbxPassword.getValue())) {
            showNotify("成功登录！", MessageBoxIcon.Success);
        } else {
            showNotify("用户名或密码错误！", MessageBoxIcon.Error);
        }
    }
}
