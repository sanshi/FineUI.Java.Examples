package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.enums.IconFont;
import com.fineui.java.examples.code.PageBase;

/**
 * 字体图标按钮演示页（路由 {@code button/button-icon-font}）：按钮使用字体图标及其四向位置；
 * 点击最后一个按钮在三个音量图标之间循环切换（服务端改 iconFont，增量推回客户端）。
 * 初始图标由模板声明（{@code icon-font="_VolumeUp"}），无需在 Page_Load 里再设。
 */
@FineUIPage("button/button-icon-font")
public class ButtonIconFont extends PageBase {

    com.fineui.java.core.controls.Button btnCustomIconFont;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnCustomIconFont_Click(Object sender, EventArgs e) {
        if (btnCustomIconFont.getIconFont() == IconFont._VolumeUp) {
            btnCustomIconFont.setIconFont(IconFont._VolumeDown);
        } else if (btnCustomIconFont.getIconFont() == IconFont._VolumeDown) {
            btnCustomIconFont.setIconFont(IconFont._VolumeOff);
        } else {
            btnCustomIconFont.setIconFont(IconFont._VolumeUp);
        }
    }
}
