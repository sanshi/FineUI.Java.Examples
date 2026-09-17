package com.fineui.java.examples.thirdparty;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/** Spectrum 颜色选择器示例。 */
@FineUIPage("third-party/color-picker")
public class ColorPicker extends PageBase {

    protected DatePicker DatePicker1;
    protected TextBox tbxMyBox;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotify("日期一：" + DatePicker1.getText() + " 颜色值：" + tbxMyBox.getValue());
    }
}
