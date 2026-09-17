package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 标签演示页（路由 {@code form/label}）：普通标签自动编码、encode-text=false 输出可信 HTML、
 * 自定义样式；按钮启用/禁用最后一个标签。
 */
@FineUIPage("form/label")
public class Label extends PageBase {

    com.fineui.java.core.controls.Label Label3;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnChangeEnable_Click(Object sender, EventArgs e) {
        Label3.setEnabled(!Label3.isEnabled());
    }
}
