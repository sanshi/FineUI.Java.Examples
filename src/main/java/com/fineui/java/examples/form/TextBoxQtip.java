package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 文本框提示信息演示页（路由 {@code form/text-box-qtip}）：演示 qtip 提示的两种设置方式与作用范围——
 * 用户名经 {@code <f:attribute>} 声明式设置（标签和输入框都提示），密码经页面脚本给标签设置（仅标签提示）。
 * 表单登录逻辑与 form/text-box 相同：客户端必填校验通过后回发，服务端回填结果标签。
 */
@FineUIPage("form/text-box-qtip")
public class TextBoxQtip extends PageBase {

    com.fineui.java.core.controls.TextBox tbxUserName;
    com.fineui.java.core.controls.TextBox tbxPassword;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setText("用户名：" + tbxUserName.getValue() + " 密码：" + tbxPassword.getValue());
    }
}
