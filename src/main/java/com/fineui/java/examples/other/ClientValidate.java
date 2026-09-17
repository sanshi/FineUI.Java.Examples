package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 客户端验证演示页面模型类（路由 {@code other/client-validate}）：注册按钮在标准表单验证（validate-forms）
 * 之外追加客户端逻辑——密码必须正好 6 个字符，否则 {@code onClientValidateClick()} 返回 false（listener 返回 false
 * 阻止服务端回发）；通过后回发，
 * 服务端弹出「表单验证通过！」。
 */
@FineUIPage("other/client-validate")
public class ClientValidate extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnRegister_Click(Object sender, EventArgs e) {
        showAlert("表单验证通过！");
    }
}
