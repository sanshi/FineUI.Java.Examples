package com.fineui.java.examples.mobile.message;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.mobile.MobilePageBase;

/**
 * 移动端提示对话框演示页（路由 {@code mobile/message/alert}）：三个按钮回发弹出提示对话框（{@code F.alert}）。
 */
@FineUIPage("mobile/message/alert")
public class Alert extends MobilePageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button1_Click(Object sender, EventArgs e) {
        com.fineui.java.core.Alert.show("这是提示对话框的内容！", "标题文字",
                MessageBoxIcon.Information);
    }

    public void Button2_Click(Object sender, EventArgs e) {
        // 按钮填满 + 标题居中 + 无关闭按钮
        com.fineui.java.core.Alert alert = createAlert();
        alert.setButtonFill(true);
        alert.setTitleAlign("center");
        alert.setEnableClose(false);
        alert.show();
    }

    public void Button3_Click(Object sender, EventArgs e) {
        // 简洁按钮 + 标题居中 + 无关闭按钮
        com.fineui.java.core.Alert alert = createAlert();
        alert.setButtonPlain(true);
        alert.setTitleAlign("center");
        alert.setEnableClose(false);
        alert.show();
    }

    private static com.fineui.java.core.Alert createAlert() {
        com.fineui.java.core.Alert alert = new com.fineui.java.core.Alert();
        alert.setMessage("这是提示对话框的内容！");
        alert.setTitle("标题文字");
        alert.setMessageBoxIcon(MessageBoxIcon.Information);
        return alert;
    }
}
