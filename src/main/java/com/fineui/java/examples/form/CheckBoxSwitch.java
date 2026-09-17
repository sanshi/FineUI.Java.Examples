package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RawHtml;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 开关样式复选框演示页（路由 {@code form/check-box-switch}）：DisplayType=Switch 的开关外观，
 * 服务端/客户端切换选中态、启用禁用；CheckBox2 用 OnCheckedChanged 勾选态改变时自动回发通知；
 * 开关文本演示默认 ON/OFF、自定义 1/0、开启/关闭，以及服务端首屏设置开关文本。
 * 由真实 F.js 渲染、纯 JSON 回发。
 */
@FineUIPage("form/check-box-switch")
public class CheckBoxSwitch extends PageBase {

    // 页面类名 CheckBoxSwitch 不与控件类同名，此处仍用全限定名与本包同风格保持一致。
    com.fineui.java.core.controls.CheckBox CheckBox1;
    com.fineui.java.core.controls.CheckBox CheckBox2;
    com.fineui.java.core.controls.CheckBox CheckBox5;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 服务端首屏用可信 HTML（值 + 布尔信任标记两属性形式）设置图标字体开关文本：开发者信任的 HTML 原样输出、不转义。
            CheckBox5.setSwitchOnTextRawHtml(new RawHtml("<i class=\"f-icon f-iconfont f-iconfont-check\"></i>"));
            CheckBox5.setSwitchOffTextRawHtml(new RawHtml("<i class=\"f-icon f-iconfont f-iconfont-close\"></i>"));
        }
    }

    public void btnSelectCheckBox_Click(Object sender, EventArgs e) {
        CheckBox1.setChecked(!CheckBox1.isChecked());
    }

    public void btnDisableCheckBox_Click(Object sender, EventArgs e) {
        CheckBox1.setEnabled(!CheckBox1.isEnabled());
    }

    public void CheckBox2_CheckedChanged(Object sender, EventArgs e) {
        showNotify("复选框的状态：" + (CheckBox2.isChecked() ? "选中" : "未选中"));
    }

    public void btnChangeText_Click(Object sender, EventArgs e) {
        CheckBox1.setText("复选框（" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "）");
    }
}
