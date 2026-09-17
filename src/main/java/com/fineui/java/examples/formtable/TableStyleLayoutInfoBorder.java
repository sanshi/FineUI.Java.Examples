package com.fineui.java.examples.formtable;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

/** 表格样式——信息录入（显示边框，路由 {@code form-table/table-style-layout-info-border}）：相比无边框版引入去边框样式表、窗体内边距为 0、中间面板加左侧分隔线。 */
@FineUIPage("form-table/table-style-layout-info-border")
public class TableStyleLayoutInfoBorder extends PageBase {

    protected com.fineui.java.core.controls.Form Form1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(Form1));
    }
}
