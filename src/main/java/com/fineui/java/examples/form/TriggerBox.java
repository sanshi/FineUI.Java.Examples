package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Window;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.TwinTriggerBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 触发按钮文本框演示页（路由 {@code form/trigger-box}）：点击输入框（enable-click-action）触发 triggerclick
 * 客户端事件弹出模态窗口；窗口内按钮点击回发、服务端关闭窗口并回填文本。
 */
@FineUIPage("form/trigger-box")
public class TriggerBox extends PageBase {

    Window Window1;
    com.fineui.java.core.controls.TriggerBox TriggerBox1;
    TwinTriggerBox TwinTriggerBox1;
    CheckBox cbxEnableEdit;
    CheckBox cbxEnableClickAction;
    Label labResult;
    Label labTriggerResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnCloseWindow_Click(Object sender, EventArgs e) {
        Window1.setHidden(true);
        TriggerBox1.setValue("弹出窗口被关闭了");
    }

    public void btnApplyOptions_Click(Object sender, EventArgs e) {
        TriggerBox1.setEnableEdit(cbxEnableEdit.isChecked());
        TwinTriggerBox1.setEnableEdit(cbxEnableEdit.isChecked());
        TwinTriggerBox1.setEnableClickAction(cbxEnableClickAction.isChecked());
    }

    public void btnReadOptions_Click(Object sender, EventArgs e) {
        labResult.setText("单触发器允许编辑：" + formatBoolean(TriggerBox1.isEnableEdit())
                + "；双触发器允许编辑：" + formatBoolean(TwinTriggerBox1.isEnableEdit())
                + "；双触发器点击触发：" + formatBoolean(TwinTriggerBox1.isEnableClickAction())
                + "；单触发器文本：" + TriggerBox1.getValue() + "；双触发器文本：" + TwinTriggerBox1.getValue());
    }

    public void btnToggleReadonly_Click(Object sender, EventArgs e) {
        boolean readOnly = !TriggerBox1.isReadonly();
        TriggerBox1.setReadonly(readOnly);
        TwinTriggerBox1.setReadonly(readOnly);
    }

    public void btnToggleEnabled_Click(Object sender, EventArgs e) {
        boolean enabled = !TriggerBox1.isEnabled();
        TriggerBox1.setEnabled(enabled);
        TwinTriggerBox1.setEnabled(enabled);
    }

    public void TwinTriggerBox1_Trigger1Click(Object sender, EventArgs e) {
        labTriggerResult.setText("执行第一个触发按钮");
    }

    public void TwinTriggerBox1_Trigger2Click(Object sender, EventArgs e) {
        labTriggerResult.setText("执行第二个触发按钮");
    }

    private static String formatBoolean(boolean value) {
        return value ? "是" : "否";
    }
}
