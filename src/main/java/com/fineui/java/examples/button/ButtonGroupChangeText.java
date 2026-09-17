package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 按钮分组（改变按钮文本）演示页（路由 {@code button/button-group-change-text}）：在服务端修改分组内某个按钮的
 * 文本、以及显示/隐藏该按钮（无论按钮位于工具栏中的分组还是独立分组）。
 */
@FineUIPage("button/button-group-change-text")
public class ButtonGroupChangeText extends PageBase {

    private static final DateTimeFormatter NOW_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Button Button4;
    Button Button8;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnChangeButtonText_Click(Object sender, EventArgs e) {
        Button4.setText(Button4.getText().length() == 3 ? "按钮四（" + LocalDateTime.now().format(NOW_FORMAT) + "）" : "按钮四");
    }

    public void btnShowHideButton_Click(Object sender, EventArgs e) {
        Button4.setHidden(!Button4.isHidden());
    }

    public void btnChangeButtonText2_Click(Object sender, EventArgs e) {
        Button8.setText(Button8.getText().length() == 3 ? "按钮八（" + LocalDateTime.now().format(NOW_FORMAT) + "）" : "按钮八");
    }

    public void btnShowHideButton2_Click(Object sender, EventArgs e) {
        Button8.setHidden(!Button8.isHidden());
    }
}
