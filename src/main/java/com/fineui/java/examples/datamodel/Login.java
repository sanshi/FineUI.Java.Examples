package com.fineui.java.examples.datamodel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数据模型手写登录（路由 {@code data-model/login}）：对照 {@code login-model} 的手工绑定版本——
 * 字段标签/校验规则直接写在模板上，回发时从控件 {@code getValue()} 读取用户名/密码比对。
 */
@FineUIPage("data-model/login")
public class Login extends PageBase {
    TextBox tbxUserName;
    TextBox tbxPassword;

    public void btnLogin_Click(Object sender, EventArgs e) {
        // getValue() 永不返回 null，可直接 trim
        String userName = tbxUserName.getValue().trim();
        String password = tbxPassword.getValue().trim();
        if ("admin".equals(userName) && "admin888".equals(password)) {
            showNotify("成功登录！", MessageBoxIcon.Success);
        } else {
            showNotify("用户名（" + userName + "）或密码（" + password + "）错误！", MessageBoxIcon.Error);
        }
    }
}
