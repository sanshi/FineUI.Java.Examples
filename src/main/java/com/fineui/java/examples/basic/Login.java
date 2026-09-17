package com.fineui.java.examples.basic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 登录表单演示页面模型类（路由 {@code basic/login}）：Window ▸ Form ▸ 2×TextBox（用户名/密码，
 * 必填 + 红星）+ 底部 Toolbar ▸ 登录(提交/主色/校验) + 重置。
 *
 * <p>由真实 F.js 渲染、纯 JSON 回发；控件字段按字段名反射注入。点「登录」时 F.js 先做必填校验，
 * 通过后回发；服务端校验 admin/admin 并 {@link #showNotify} 弹出 F.notify 通知——全程无整页刷新、无 eval。
 */
@FineUIPage("basic/login")
public class Login extends PageBase {

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
