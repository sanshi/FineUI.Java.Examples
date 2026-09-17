package com.fineui.java.examples.mobile.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.mobile.MobilePageBase;

/**
 * 移动端系统登录（简洁校验框）演示页（路由 {@code mobile/form/login-validate-message-box-plain}）：登录按钮内置表单校验，
 * 校验失败弹出简洁样式的消息框；通过后回发校验账号，居中通知登录结果。
 */
@FineUIPage("mobile/form/login-validate-message-box-plain")
public class LoginValidateMessageBoxPlain extends MobilePageBase {

    TextBox tbxUserName;
    TextBox tbxPassword;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnLogin_Click(Object sender, EventArgs e) {
        if ("admin".equals(tbxUserName.getValue()) && "admin".equals(tbxPassword.getValue())) {
            showCenterNotify("成功登录！", MessageBoxIcon.Success);
        } else {
            showCenterNotify("用户名或密码错误！", MessageBoxIcon.Error);
        }
    }
}
