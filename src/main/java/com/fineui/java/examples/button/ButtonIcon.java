package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 图片图标按钮演示页（路由 {@code button/button-icon}）：具名图标与自定义图片图标及其四向位置；
 * 点击「自定义图标」按钮在两张图片之间切换（服务端改图标地址，增量推回客户端）。
 */
@FineUIPage("button/button-icon")
public class ButtonIcon extends PageBase {

    com.fineui.java.core.controls.Button btnCustomIcon;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnCustomIcon_Click(Object sender, EventArgs e) {
        if (btnCustomIcon.getIconUrl().endsWith("1.png")) {
            btnCustomIcon.setIconUrl("/res/images/16/8.png");
        } else {
            btnCustomIcon.setIconUrl("/res/images/16/1.png");
        }
    }
}
