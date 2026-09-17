package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.enums.IconFont;
import com.fineui.java.examples.code.PageBase;

/**
 * 圆角字体图标按钮演示页（路由 {@code button/button-icon-font-round-corner}）：在字体图标按钮基础上启用圆角，
 * 并展示不同尺寸；点击最后一个按钮在三个音量图标之间循环切换。
 */
@FineUIPage("button/button-icon-font-round-corner")
public class ButtonIconFontRoundCorner extends PageBase {

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
