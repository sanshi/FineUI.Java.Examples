package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 复选框演示页（路由 {@code form/check-box}）：单个复选框的选中/反选（服务端 {@code setChecked} 与客户端脚本 {@code setValue}）、
 * 启用/禁用、改变文本；另一个复选框演示 {@code OnCheckedChanged}——勾选态改变时回发、服务端读回状态并回填结果标签。
 * 由真实 F.js 渲染、纯 JSON 回发。
 */
@FineUIPage("form/check-box")
public class CheckBox extends PageBase {

    // 页面类名 CheckBox 与控件类同名，控件字段用全限定名消歧。
    com.fineui.java.core.controls.CheckBox CheckBox1;
    com.fineui.java.core.controls.CheckBox CheckBox2;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectCheckBox_Click(Object sender, EventArgs e) {
        CheckBox1.setChecked(!CheckBox1.isChecked());
    }

    public void btnDisableCheckBox_Click(Object sender, EventArgs e) {
        CheckBox1.setEnabled(!CheckBox1.isEnabled());
    }

    public void btnChangeText_Click(Object sender, EventArgs e) {
        CheckBox1.setText("复选框（" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "）");
    }

    public void CheckBox2_CheckedChanged(Object sender, EventArgs e) {
        labResult.setText("复选框的状态：" + (CheckBox2.isChecked() ? "选中" : "未选中"));
    }
}
