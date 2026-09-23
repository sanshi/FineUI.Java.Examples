package com.fineui.java.examples.message;

import com.fineui.java.core.CustomEventArgs;
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
 * 输入对话框演示页（路由 {@code message/prompt}）：表单里配置「消息提示 / 缺省值 / 标题 / 是否可关闭 /
 * 是否必填 / 是否多行（及高度）/ 是否密码输入 / 图标 / ID / 宽高限制 / 弹出位置」等属性，点击按钮回发后
 * 由服务端弹出 {@code F.prompt} 输入框；点确定后把用户输入经页面回调函数 {@code promptOKCallback} 回显。
 */
@FineUIPage("message/prompt")
public class Prompt extends PageBase {

    protected TextBox tbxMessage;
    protected TextBox tbxDefaultValue;
    protected TextBox tbxTitle;
    protected CheckBox cbxEnableClose;
    protected CheckBox cbxRequired;
    protected CheckBox cbxIsMultiLine;
    protected NumberBox nbMultiLineHeight;
    protected CheckBox cbxIsPassword;
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

        boolean multiLine = cbxIsMultiLine.isChecked();
        Integer multiLineHeight = multiLine ? intOrNull(nbMultiLineHeight.getValue()) : null;
        String inputType = null;
        if (!multiLine && cbxIsPassword.isChecked()) {
            inputType = "password";
        }

        showPrompt(tbxMessage.getValue(), tbxTitle.getValue(), icon, target,
                multiLine, multiLineHeight, inputType,
                tbxDefaultValue.getValue(), cbxRequired.isChecked(),
                tbxID.getValue(), cbxEnableClose.isChecked(),
                intOrNull(nbWidth.getValue()), intOrNull(nbMinWidth.getValue()), intOrNull(nbMaxWidth.getValue()),
                "promptOKCallback");
    }

    public void btnCallback_Click(Object sender, EventArgs e) {
        showPrompt("请输入新名称", "具名回调", MessageBoxIcon.Question,
                "onPromptAccepted", "onPromptDismissed");
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("PromptAccepted".equals(e.getEventName())) {
            showNotify("确定输入：" + e.getArgument());
        } else if ("PromptDismissed".equals(e.getEventName())) {
            showNotify("取消输入：" + e.getArgument());
        }
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
