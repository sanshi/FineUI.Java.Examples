package com.fineui.java.examples.multilang;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.MultilangPageBase;
import org.springframework.context.MessageSource;

/**
 * 多语言登录（路由 {@code multi-lang/login}）：窗口标题/字段标签/按钮/校验消息文本均走
 * 多语言资源（切换语言随之变化）；登录成功/失败通知文本含 {@code {0}}/{@code {1}} 占位符。
 */
@FineUIPage("multi-lang/login")
public class Login extends MultilangPageBase {

    TextBox tbxUserName;
    TextBox tbxPassword;

    public Login(MessageSource messageSource) {
        super(messageSource);
    }

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnLogin_Click(Object sender, EventArgs e) {
        String userName = trim(tbxUserName.getValue());
        String password = trim(tbxPassword.getValue());

        if ("admin".equals(userName) && "admin888".equals(password)) {
            showNotify(_R("multilang.login.success"), MessageBoxIcon.Success);
        } else {
            showNotify(_R("multilang.login.errorFormat", userName, password), MessageBoxIcon.Error);
        }
    }

    private static String trim(String value) {
        return value == null ? "" : value.trim();
    }
}
