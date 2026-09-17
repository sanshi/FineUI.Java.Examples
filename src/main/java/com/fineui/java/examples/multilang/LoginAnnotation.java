package com.fineui.java.examples.multilang;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.code.MultilangPageBase;
import com.fineui.java.examples.multilang.model.User;
import org.springframework.context.MessageSource;

/**
 * 多语言登录（数据注解，路由 {@code multi-lang/login-annotation}）：表单字段走 {@code for} 绑定 +
 * {@code @Display(nameKey)}/校验注解（{@code #{资源键}}），标签与校验消息均随语言切换。
 */
@FineUIPage("multi-lang/login-annotation")
public class LoginAnnotation extends MultilangPageBase {

    public LoginAnnotation(MessageSource messageSource) {
        super(messageSource);
    }

    // 本页没有首屏初值要加载，故不写 Page_Get：字段保持 null，框架会兜底新建一个空实例承载表单值。
    @BindProperty
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void btnLogin_Click(Object sender, EventArgs e) {
        if (!getModelState().isValid()) {
            return;
        }
        if ("admin".equals(currentUser.getUserName()) && "admin888".equals(currentUser.getPassword())) {
            showNotify(_R("multilang.login.success"), MessageBoxIcon.Success);
        } else {
            showNotify(_R("multilang.login.errorFormat", currentUser.getUserName(), currentUser.getPassword()),
                    MessageBoxIcon.Error);
        }
    }
}
