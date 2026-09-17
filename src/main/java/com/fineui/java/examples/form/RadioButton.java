package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 单选按钮演示页（路由 {@code form/radio-button}）：独立单选框的选中/反选（服务端 {@code setChecked} 与客户端脚本）、
 * 分组单选（同 {@code group-name} 互斥）与服务端"选中分组内下一项"、以及 {@code OnCheckedChanged} 自动回发通知选中项。
 * 由真实 F.js 渲染、纯 JSON 回发。
 */
@FineUIPage("form/radio-button")
public class RadioButton extends PageBase {

    // 页面类名 RadioButton 与控件类同名，控件字段用全限定名消歧。
    com.fineui.java.core.controls.RadioButton rbtnSingleRadio;
    com.fineui.java.core.controls.RadioButton rbtnFirst;
    com.fineui.java.core.controls.RadioButton rbtnSecond;
    com.fineui.java.core.controls.RadioButton rbtnThird;
    com.fineui.java.core.controls.RadioButton rbtnFirstAuto;
    com.fineui.java.core.controls.RadioButton rbtnSecondAuto;
    com.fineui.java.core.controls.RadioButton rbtnThirdAuto;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectSingleRadio_Click(Object sender, EventArgs e) {
        rbtnSingleRadio.setChecked(!rbtnSingleRadio.isChecked());
    }

    public void btnSelectSecondRadio_Click(Object sender, EventArgs e) {
        // 找到分组内当前选中的项，选中它的下一项（同分组名，客户端原生 radio 互斥自动取消其余项）。
        com.fineui.java.core.controls.RadioButton[] radios = {rbtnFirst, rbtnSecond, rbtnThird};
        for (int i = 0; i < radios.length; i++) {
            if (radios[i].isChecked()) {
                radios[(i + 1) % radios.length].setChecked(true);
                break;
            }
        }
    }

    public void rbtnAuto_CheckedChanged(Object sender, EventArgs e) {
        String checkedValue = "";
        if (rbtnFirstAuto.isChecked()) {
            checkedValue = rbtnFirstAuto.getText();
        } else if (rbtnSecondAuto.isChecked()) {
            checkedValue = rbtnSecondAuto.getText();
        } else if (rbtnThirdAuto.isChecked()) {
            checkedValue = rbtnThirdAuto.getText();
        }
        showNotify("单选框选中项：" + checkedValue);
    }
}
