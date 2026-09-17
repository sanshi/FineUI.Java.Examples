package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 文本框演示页（路由 {@code form/text-box}）：一个登录表单——用户名/密码两个必填输入框（带红星，密码框为密码模式），
 * 「登录」按钮在客户端完成必填校验后回发、服务端读回输入值并回填到结果标签；「重置」在客户端清空表单。
 *
 * <p>由真实 F.js 渲染、纯 JSON 回发：点「登录」时 F.js 先校验 {@code SimpleForm1} 的必填项，通过后才回发；
 * 服务端 {@link #btnSubmit_Click} 用 {@code getValue()} 读回用户名/密码并 {@code setText} 写入标签，增量推回客户端。
 */
@FineUIPage("form/text-box")
public class TextBox extends PageBase {

    // 页面类名 TextBox 与控件类同名，控件字段用全限定名消歧（对齐 button/Button 示例的写法）。
    com.fineui.java.core.controls.TextBox tbxUserName;
    com.fineui.java.core.controls.TextBox tbxPassword;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setText("用户名：" + tbxUserName.getValue() + " 密码：" + tbxPassword.getValue());
    }
}
