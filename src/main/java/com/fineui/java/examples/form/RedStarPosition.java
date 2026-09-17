package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 必填红星位置演示页（路由 {@code form/red-star-position}）：默认红星在标签文本之后；
 * 第二个表单 red-star-position=BeforeText + label-align=Right，红星在文本之前。
 */
@FineUIPage("form/red-star-position")
public class RedStarPosition extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnLogin_Click(Object sender, EventArgs e) {
        showNotify("表单一验证通过、已提交");
    }

    public void btnLogin2_Click(Object sender, EventArgs e) {
        showNotify("表单二验证通过、已提交");
    }

    public void btnLogin3_Click(Object sender, EventArgs e) {
        showNotify("表单三验证通过、已提交");
    }
}
