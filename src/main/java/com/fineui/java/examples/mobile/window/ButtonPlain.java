package com.fineui.java.examples.mobile.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.mobile.MobilePageBase;

/**
 * 移动端登陆窗体（简洁按钮）演示页（路由 {@code mobile/window/button-plain}）：登录/取消按钮放在窗体底部
 * 简洁样式工具栏内，标题居中且不显示关闭按钮。
 */
@FineUIPage("mobile/window/button-plain")
public class ButtonPlain extends MobilePageBase {

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
