package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.LoginForm;

/**
 * 数据模型登录绑定（路由 {@code data-model/login-model}）：登录表单经 {@code for} 绑定到
 * {@link LoginForm}，字段标签/必填/长度/正则等全部由模型上的 {@code @Display} 与 Jakarta 校验注解驱动
 * （模板不写 Label/Required），提交回发后自动绑定 + 验证并比对凭据。
 */
@FineUIPage("data-model/login-model")
public class LoginModel extends PageBase {
    // 本页没有首屏初值要加载，故不写 Page_Get：字段保持 null，框架会兜底新建一个空实例承载表单值。
    @BindProperty
    private LoginForm currentUser;

    public LoginForm getCurrentUser() {
        return currentUser;
    }

    public void btnLogin_Click(Object sender, EventArgs e) {
        if (getModelState().isValid()) {
            if (!"admin".equals(currentUser.getUserName()) || !"admin888".equals(currentUser.getPassword())) {
                // 业务校验失败：回显到字段（红框），不回显密码
                getModelState().addFieldError("currentUser.userName", "用户名或密码错误！");
                return;
            }
            showNotify("成功登录！", MessageBoxIcon.Success);
        }
    }
}
