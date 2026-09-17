package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单字段的 Label 样式演示页面模型类（路由 {@code other/field-label-style}）：表单内两个文本框带
 * CssClass（head 里定义了 .red/.blue 两个样式类，作用于字段 Label），按钮在服务端把两个文本框的
 * CssClass 在 red ↔ blue 之间切换。
 */
@FineUIPage("other/field-label-style")
public class FieldLabelStyle extends PageBase {

    TextBox tbxUserName;
    TextBox tbxPassword;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 切换两个文本框的 CssClass（red ↔ blue）。 */
    public void btnSwitchClass_Click(Object sender, EventArgs e) {
        if (!"red".equals(tbxUserName.getCssClass())) {
            tbxUserName.setCssClass("red");
            tbxPassword.setCssClass("red");
        } else {
            tbxUserName.setCssClass("blue");
            tbxPassword.setCssClass("blue");
        }
    }
}
