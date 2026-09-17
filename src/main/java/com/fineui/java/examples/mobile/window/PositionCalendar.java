package com.fineui.java.examples.mobile.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.mobile.MobilePageBase;

/**
 * 移动端选择日期演示页（路由 {@code mobile/window/position-calendar}）：从底部弹出日历，选中日期后隐藏面板并居中通知所选日期。
 * 全部为客户端交互，无服务端逻辑。
 */
@FineUIPage("mobile/window/position-calendar")
public class PositionCalendar extends MobilePageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
