package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.RadioButton;
import com.fineui.java.examples.code.PageBase;

/**
 * 单选按钮自定义文字样式演示页（路由 {@code form/radio-button-custom-text-style}）：与单选按钮页相同的交互，
 * 额外用纯 CSS（{@code :has(input:checked)}）让<b>选中项</b>的文字标签变红、并随选中项切换而跟随。
 * 由真实 F.js 渲染、纯 JSON 回发。
 */
@FineUIPage("form/radio-button-custom-text-style")
public class RadioButtonCustomTextStyle extends PageBase {

    RadioButton rbtnSingleRadio;
    RadioButton rbtnFirst;
    RadioButton rbtnSecond;
    RadioButton rbtnThird;
    RadioButton rbtnFirstAuto;
    RadioButton rbtnSecondAuto;
    RadioButton rbtnThirdAuto;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectSingleRadio_Click(Object sender, EventArgs e) {
        rbtnSingleRadio.setChecked(!rbtnSingleRadio.isChecked());
    }

    public void btnSelectSecondRadio_Click(Object sender, EventArgs e) {
        RadioButton[] radios = {rbtnFirst, rbtnSecond, rbtnThird};
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
