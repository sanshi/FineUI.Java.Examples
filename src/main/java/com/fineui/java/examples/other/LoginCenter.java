package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单居中显示演示页面模型类（路由 {@code other/login-center}）：登录表单通过 {@code f-shadow} 加阴影、
 * 经 head 里的 CSS 居中；登录按钮服务端校验 admin/admin，
 * 成功/失败分别弹出成功/错误通知。
 */
@FineUIPage("other/login-center")
public class LoginCenter extends PageBase {

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
