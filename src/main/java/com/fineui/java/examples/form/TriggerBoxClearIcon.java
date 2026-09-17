package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TriggerBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 触发按钮文本框·清除图标演示页（路由 {@code form/trigger-box-clear-icon}）：隐藏触发按钮、有内容时显示清除图标；
 * 提交按钮回读当前输入值。
 */
@FineUIPage("form/trigger-box-clear-icon")
public class TriggerBoxClearIcon extends PageBase {

    TriggerBox TriggerBox1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showAlert("文本框的输入值：" + TriggerBox1.getValue());
    }
}
