package com.fineui.java.examples.formtable;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 表格样式——信息录入（路由 {@code form-table/table-style-layout-info}）：表格化表单 + HBox 三栏（5:3:2，含头像上传）+ 下方多行双列；保存弹出字段值汇总。 */
@FineUIPage("form-table/table-style-layout-info")
public class TableStyleLayoutInfo extends PageBase {

    protected com.fineui.java.core.controls.Form Form1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
