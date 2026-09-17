package com.fineui.java.examples.mobile.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.mobile.MobilePageBase;

/**
 * 移动端登陆窗体演示页（路由 {@code mobile/window/window}）：点按钮弹出模态登录窗体，
 * 窗体内表单校验通过后回发校验账号并居中通知结果。
 */
@FineUIPage("mobile/window/window")
public class Window extends MobilePageBase {

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
