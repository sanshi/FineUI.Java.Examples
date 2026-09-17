package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 徽标按钮演示页（路由 {@code button/button-badge}）：按钮的徽标（数字/文本、类型、动画）与图标组合；
 * 点击可切换自定义图标、以及给徽标数字加一（服务端改属性增量推回客户端）。
 */
@FineUIPage("button/button-badge")
public class ButtonBadge extends PageBase {

    com.fineui.java.core.controls.Button btnCustomIcon;
    com.fineui.java.core.controls.Button btnBadgeNumber;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnCustomIcon_Click(Object sender, EventArgs e) {
        if (btnCustomIcon.getIconUrl().endsWith("1.png")) {
            btnCustomIcon.setIconUrl("/res/images/16/8.png");
        } else {
            btnCustomIcon.setIconUrl("/res/images/16/1.png");
        }
    }

    public void btnChangeBadge_Click(Object sender, EventArgs e) {
        int number = Integer.parseInt(btnBadgeNumber.getBadgeText());
        btnBadgeNumber.setBadgeText(String.valueOf(number + 1));
    }
}
