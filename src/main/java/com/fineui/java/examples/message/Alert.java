package com.fineui.java.examples.message;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.RadioButtonList;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.core.enums.Target;
import com.fineui.java.examples.code.PageBase;

/**
 * 提示对话框演示页（路由 {@code message/alert}）：表单里配置「消息提示 / 标题 / 图标 / 是否可关闭 /
 * 对话框 ID / 宽高限制 / 弹出位置」等属性，点击按钮回发后由服务端弹出 {@code F.alert} 消息框。
 */
@FineUIPage("message/alert")
public class Alert extends PageBase {

    protected TextBox tbxMessage;
    protected TextBox tbxTitle;
    protected CheckBox cbxEnableClose;
    protected RadioButtonList rblMessageBoxIcon;
    protected TextBox tbxID;
    protected NumberBox nbWidth;
    protected NumberBox nbMinWidth;
    protected NumberBox nbMaxWidth;
    protected RadioButtonList rblTarget;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        MessageBoxIcon icon = messageBoxIcon(rblMessageBoxIcon.getSelectedValue());
        Target target = target(rblTarget.getSelectedValue());

        String id = tbxID.getValue();
        boolean enableClose = cbxEnableClose.isChecked();
        Integer width = intOrNull(nbWidth.getValue());
        Integer minWidth = intOrNull(nbMinWidth.getValue());
        Integer maxWidth = intOrNull(nbMaxWidth.getValue());

        showAlert(tbxMessage.getValue(), tbxTitle.getValue(), icon, target, id, enableClose, width, minWidth, maxWidth);
    }

    /** 单选值（如 "Warning"）→ 消息框图标枚举；无法识别回落无图标。 */
    private static MessageBoxIcon messageBoxIcon(String name) {
        if (name == null) {
            return MessageBoxIcon.None;
        }
        for (MessageBoxIcon icon : MessageBoxIcon.values()) {
            if (icon.name().equalsIgnoreCase(name)) {
                return icon;
            }
        }
        return MessageBoxIcon.None;
    }

    /** 单选值（如 "Top"）→ 弹出位置枚举；无法识别回落当前页面。 */
    private static Target target(String name) {
        if (name == null) {
            return Target.Self;
        }
        for (Target target : Target.values()) {
            if (target.name().equalsIgnoreCase(name)) {
                return target;
            }
        }
        return Target.Self;
    }

    /** 数字输入框文本 → Integer；空/非法返回 null（不设置该属性）。 */
    private static Integer intOrNull(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
