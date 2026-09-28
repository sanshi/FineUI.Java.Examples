package com.fineui.java.examples.block;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/** Block 响应式表单，三个按钮分别验证所在表单并报告提交结果。 */
@FineUIPage("block/form")
public class Form extends PageBase {

    public void btnSubmitForm1_Click(Object sender, EventArgs e) {
        showNotify("表单 1 验证并提交成功！");
    }

    public void btnSubmitForm2_Click(Object sender, EventArgs e) {
        showNotify("表单 2 验证并提交成功！");
    }

    public void btnSubmitForm3_Click(Object sender, EventArgs e) {
        showNotify("表单 3 验证并提交成功！");
    }
}
