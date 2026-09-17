package com.fineui.java.examples.formlayout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 复杂布局——信息录入（路由 {@code form-layout/layout-info}）：窗体内 HBox 三栏（5:3:2）+ 下方多行双列表单，含头像上传；保存弹出字段值汇总。 */
@FineUIPage("form-layout/layout-info")
public class LayoutInfo extends PageBase {

    protected com.fineui.java.core.controls.Form Form1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
