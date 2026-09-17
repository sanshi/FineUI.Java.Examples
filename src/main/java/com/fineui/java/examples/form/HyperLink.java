package com.fineui.java.examples.form;

import com.fineui.java.core.Alert;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 超链接演示页（路由 {@code form/hyperlink}）：新窗口打开、文本作可信 HTML 不编码、禁用态；
 * 点击按钮启用/禁用最后一个链接，并在启用时挂上客户端点击提示。
 */
@FineUIPage("form/hyperlink")
public class HyperLink extends PageBase {

    com.fineui.java.core.controls.HyperLink HyperLink2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnChangeEnable_Click(Object sender, EventArgs e) {
        HyperLink2.setEnabled(!HyperLink2.isEnabled());
    }
}
