package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 表单比较校验（路由 {@code form/form-compare}）：日期大于、文本相等、数字大于等于、
 * 以及数字与标签（{@code compare-type="Int"}）之间的比较校验；客户端校验通过才提交。
 */
@FineUIPage("form/form-compare")
public class FormCompare extends PageBase {

    SimpleForm SimpleForm1;
    TextBox TextBox1;
    TextBox TextBox2;
    NumberBox NumberBox1;
    NumberBox NumberBox2;
    NumberBox NumberBox3;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(SimpleForm1));
    }
}
