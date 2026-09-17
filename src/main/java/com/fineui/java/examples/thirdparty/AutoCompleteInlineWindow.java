package com.fineui.java.examples.thirdparty;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/** 窗体内自动完成示例。 */
@FineUIPage("third-party/auto-complete-inline-window")
public class AutoCompleteInlineWindow extends PageBase {

    protected TextBox TextBox1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showNotify("用户输入值：" + TextBox1.getValue());
    }
}
