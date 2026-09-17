package com.fineui.java.examples.basic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.code.PageBase;

/**
 * 第一个示例（路由 {@code basic/hello}）：点击按钮弹出「你好 FineUI！」消息框——
 * 第一个按钮普通弹出（警告图标），第二个按钮在顶层窗口弹出（信息图标）。
 */
@FineUIPage("basic/hello")
public class Hello extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnHello_Click(Object sender, EventArgs e) {
        showAlert("你好 FineUI！", null, MessageBoxIcon.Warning);
    }

    public void btnHello2_Click(Object sender, EventArgs e) {
        showAlertInTop("你好 FineUI！", null, MessageBoxIcon.Information);
    }
}
