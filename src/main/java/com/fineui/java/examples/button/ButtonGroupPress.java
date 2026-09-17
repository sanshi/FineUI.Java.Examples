package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.ButtonGroup;
import com.fineui.java.examples.code.PageBase;

/**
 * 按钮分组（按下状态分组）演示页（路由 {@code button/button-group-press}）：整组按下互斥（pressGroup）、
 * 可不选（allowNonePress）、可多选（allowMultiPress）；按下状态改变时触发服务端事件、弹出当前按下的按钮列表。
 */
@FineUIPage("button/button-group-press")
public class ButtonGroupPress extends PageBase {

    ButtonGroup ButtonGroup3;
    ButtonGroup ButtonGroup4;
    ButtonGroup ButtonGroup5;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void ButtonGroup3_PressChanged(Object sender, EventArgs e) {
        showNotifyRaw(getPressedButton("ButtonGroup3", ButtonGroup3));
    }

    public void ButtonGroup4_PressChanged(Object sender, EventArgs e) {
        showNotifyRaw(getPressedButton("ButtonGroup4", ButtonGroup4));
    }

    public void ButtonGroup5_PressChanged(Object sender, EventArgs e) {
        showNotifyRaw(getPressedButton("ButtonGroup5", ButtonGroup5));
    }

    private String getPressedButton(String buttonGroupID, ButtonGroup theGroup) {
        StringBuilder sb = new StringBuilder();
        sb.append("分组 ").append(buttonGroupID).append(" 中按下的按钮：");
        if (theGroup != null) {
            sb.append("<ul>");
            for (Button btn : theGroup.getItems()) {
                if (btn.isPressed()) {
                    sb.append("<li>").append(btn.getText()).append("</li>");
                }
            }
            sb.append("</ul>");
        }
        return sb.toString();
    }
}
