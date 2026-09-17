package com.fineui.java.examples.message;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.core.enums.Position;
import com.fineui.java.core.enums.Target;
import com.fineui.java.core.enums.TextAlign;
import com.fineui.java.examples.code.PageBase;

/**
 * 通知对话框演示页（路由 {@code message/notify}）：表单里配置「消息 / 显示毫秒数 / 进度条 / 加载图 /
 * 模态 / 位置 / 内边距 / 标题栏 / 可移动可关闭 / 图标 / ID / 宽高限制」等属性，点击按钮回发后由服务端
 * 弹出 {@code F.notify} 通知框，消失时调用页面回调 {@code notifyHideCallback}。
 */
@FineUIPage("message/notify")
public class Notify extends PageBase {

    protected TextBox tbxMessage;
    protected NumberBox nbDisplayMilliseconds;
    protected CheckBox cbxDisplayProgress;
    protected CheckBox cbxShowLoading;
    protected CheckBox cbxIsModal;
    protected TextBox tbxBodyPadding;
    protected DropDownList ddlPositionX;
    protected DropDownList ddlPositionY;
    protected DropDownList ddlMessageAlign;
    protected CheckBox cbxShowHeader;
    protected TextBox tbxTitle;
    protected CheckBox cbxEnableDrag;
    protected CheckBox cbxEnableClose;
    protected TextBox tbxID;
    protected NumberBox nbWidth;
    protected NumberBox nbMinWidth;
    protected NumberBox nbMaxWidth;
    protected DropDownList ddlMessageBoxIcon;
    protected DropDownList ddlTarget;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        boolean showHeader = cbxShowHeader.isChecked();
        String title = showHeader ? tbxTitle.getValue() : null;
        boolean enableDrag = showHeader && cbxEnableDrag.isChecked();
        boolean enableClose = showHeader && cbxEnableClose.isChecked();

        showNotify(tbxMessage.getValue(), title, messageBoxIcon(ddlMessageBoxIcon.getSelectedValue()),
                target(ddlTarget.getSelectedValue()),
                showHeader, enableDrag, enableClose,
                intValue(nbDisplayMilliseconds.getValue(), 5000), cbxDisplayProgress.isChecked(),
                position(ddlPositionX.getSelectedValue(), Position.Right),
                position(ddlPositionY.getSelectedValue(), Position.Bottom),
                cbxIsModal.isChecked(),
                tbxBodyPadding.getValue(), textAlign(ddlMessageAlign.getSelectedValue()),
                cbxShowLoading.isChecked(),
                intOrNull(nbWidth.getValue()), intOrNull(nbMinWidth.getValue()), intOrNull(nbMaxWidth.getValue()),
                tbxID.getValue(), "notifyHideCallback");
    }

    /** 下拉值（如 "Warning"）→ 消息框图标枚举；无法识别回落无图标。 */
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

    /** 下拉值（如 "Top"）→ 弹出位置枚举；无法识别回落指定默认值。 */
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

    /** 下拉值（如 "Right"）→ 横向/纵向位置枚举；无法识别回落指定默认值。 */
    private static Position position(String name, Position fallback) {
        if (name == null) {
            return fallback;
        }
        for (Position position : Position.values()) {
            if (position.name().equalsIgnoreCase(name)) {
                return position;
            }
        }
        return fallback;
    }

    /** 下拉值（如 "Center"）→ 正文排列位置；无法识别回落靠左。 */
    private static TextAlign textAlign(String name) {
        if (name == null) {
            return TextAlign.Left;
        }
        for (TextAlign align : TextAlign.values()) {
            if (align.name().equalsIgnoreCase(name)) {
                return align;
            }
        }
        return TextAlign.Left;
    }

    /** 数字输入框文本 → Integer；空/非法回落指定默认值。 */
    private static int intValue(String text, int defaultValue) {
        Integer value = intOrNull(text);
        return value == null ? defaultValue : value;
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
