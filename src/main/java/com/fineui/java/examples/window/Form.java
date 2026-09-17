package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体内嵌表单演示页（路由 {@code window/form}）：窗体采用 Fit 布局填充一个表单，
 * 顶部工具栏含文本/分隔符/客户端按钮，底部工具栏含“验证并提交”与“关闭窗体”按钮；
 * 提交按钮先在客户端校验表单，通过后回发到服务端弹出提示。
 */
@FineUIPage("window/form")
public class Form extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 表单验证通过并提交后弹出提示。 */
    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotify("表单验证通过并提交！");
    }
}
