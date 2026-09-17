package com.fineui.java.examples.mobile.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.mobile.MobilePageBase;

/**
 * 移动端「点遮罩关闭」窗体演示页（路由 {@code mobile/window/hide-on-mask-click}）：两个模态窗体均可点遮罩关闭，
 * 窗体一内含按钮再打开窗体二。全部为客户端交互，无服务端逻辑。
 */
@FineUIPage("mobile/window/hide-on-mask-click")
public class HideOnMaskClick extends MobilePageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
