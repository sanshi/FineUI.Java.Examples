package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 关闭页面前的确认框（路由 {@code form/change-confirm-form}）：演示表单「脏数据」跟踪——
 * 编辑表单字段后变脏（{@code isDirty()}，页面用 {@code beforeunload} 提示放弃修改），
 * 保存数据后调用 {@code clearDirty()} 清除脏状态，避免误报。
 */
@FineUIPage("form/change-confirm-form")
public class ChangeConfirmForm extends PageBase {

    SimpleForm SimpleForm1;
    NumberBox NumberBox1;
    TextBox TextBox1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnClosePostBack_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(SimpleForm1));
        // 保存数据后，清空面板内表单字段的改变状态
        SimpleForm1.clearDirty();
    }
}
