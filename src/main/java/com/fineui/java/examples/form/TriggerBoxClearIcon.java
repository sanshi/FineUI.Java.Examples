package com.fineui.java.examples.form;

import com.fineui.java.core.Alert;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TriggerBox;
import com.fineui.java.core.controls.TwinTriggerBox;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 清除图标按当前文本自动显隐；可切换到手动模式，并通过连续回发读取保留的设置和文本。
 */
@FineUIPage("form/trigger-box-clear-icon")
public class TriggerBoxClearIcon extends PageBase {

    TriggerBox TriggerBox1;
    TwinTriggerBox TwinTriggerBox1;
    CheckBox cbxAutoClear;
    CheckBox cbxShowTrigger;
    CheckBox cbxShowTrigger1;
    CheckBox cbxShowTrigger2;
    Label labVisibility;
    Label labTriggerResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        Alert.show("文本框的输入值：" + TriggerBox1.getValue());
    }

    public void btnApplyVisibility_Click(Object sender, EventArgs e) {
        boolean automatic = cbxAutoClear.isChecked();
        TriggerBox1.setAutoShowClearIcon(automatic);
        TwinTriggerBox1.setAutoShowClearIcon(automatic);
        cbxShowTrigger.setEnabled(!automatic);
        cbxShowTrigger1.setEnabled(!automatic);
        TwinTriggerBox1.setShowTrigger2(cbxShowTrigger2.isChecked());

        // 自动模式由文字接管第一按钮，不再同时写手动开关；关闭后才应用用户选择的手动值。
        if (!automatic) {
            TriggerBox1.setShowTrigger(cbxShowTrigger.isChecked());
            TwinTriggerBox1.setShowTrigger(cbxShowTrigger.isChecked());
            TwinTriggerBox1.setShowTrigger1(cbxShowTrigger1.isChecked());
        }
    }

    public void btnReadVisibility_Click(Object sender, EventArgs e) {
        labVisibility.setText("自动清除：" + formatBoolean(TriggerBox1.isAutoShowClearIcon())
                + "；单触发器手动显示：" + formatBoolean(TriggerBox1.isShowTrigger())
                + "；双触发器手动显示：" + formatBoolean(TwinTriggerBox1.isShowTrigger())
                + "；第一按钮手动显示：" + formatBoolean(TwinTriggerBox1.isShowTrigger1())
                + "；第二按钮显示：" + formatBoolean(TwinTriggerBox1.isShowTrigger2())
                + "；单触发器文本：" + TriggerBox1.getValue() + "；双触发器文本：" + TwinTriggerBox1.getValue());
    }

    public void btnFillText_Click(Object sender, EventArgs e) {
        TriggerBox1.setValue("后台文字");
        TwinTriggerBox1.setValue("后台文字");
    }

    public void btnClearText_Click(Object sender, EventArgs e) {
        TriggerBox1.setValue("");
        TwinTriggerBox1.setValue("");
    }

    public void TwinTriggerBox1_Trigger2Click(Object sender, EventArgs e) {
        labTriggerResult.setText("执行第二个触发按钮");
    }

    private static String formatBoolean(boolean value) {
        return value ? "是" : "否";
    }
}
