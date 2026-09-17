package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Form;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.TextArea;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 更新标签和校验规则（路由 {@code form/form-update-label}）：用客户端监听在运行时更新字段——
 * 改标签文本（{@code setFieldLabel}）、去除必填与红星（{@code setRequired}/{@code setRedStar}）、
 * 去除正则/最少字符限制、修改最大值。
 */
@FineUIPage("form/form-update-label")
public class FormUpdateLabel extends PageBase {

    Form Form1;
    TextBox tbxEmail;
    NumberBox nbxApplyNumber;
    TextArea taDescription;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
