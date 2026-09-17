package com.fineui.java.examples.mobile.message;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.mobile.MobilePageBase;

/**
 * 移动端通知对话框演示页（路由 {@code mobile/message/notify}）：第一个按钮回发弹出通知（{@code F.notify}）；
 * 其余按钮客户端演示「正在加载」通知的不同样式与显示/关闭。
 */
@FineUIPage("mobile/message/notify")
public class Notify extends MobilePageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showNotify("数据保存成功！", "通知", MessageBoxIcon.Information);
    }
}
