package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/** 表单禁用与只读（路由 {@code form/form-disabled}）：客户端批量禁用/启用/只读字段、标记/清除无效、禁用整个表单面板；提交弹出字段值汇总。 */
@FineUIPage("form/form-disabled")
public class FormDisabled extends PageBase {

    protected com.fineui.java.core.controls.Form Form1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
