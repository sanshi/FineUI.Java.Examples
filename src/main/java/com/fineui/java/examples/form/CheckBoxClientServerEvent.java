package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 复选框客户端/服务端事件执行顺序演示页（路由 {@code form/check-box-client-server-event}）：
 * CheckBox1 勾选态改变时先执行客户端 change 监听、再执行服务端 OnCheckedChanged，最终由服务端结果写入结果标签；
 * CheckBox2 的客户端 change 监听显式 {@code return false}，阻止服务端事件回发（只执行客户端）。
 * 由真实 F.js 渲染、纯 JSON 回发。
 */
@FineUIPage("form/check-box-client-server-event")
public class CheckBoxClientServerEvent extends PageBase {

    CheckBox CheckBox1;
    CheckBox CheckBox2;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void CheckBox1_CheckedChanged(Object sender, EventArgs e) {
        labResult.setText("【服务端】复选框1的状态：" + (CheckBox1.isChecked() ? "选中" : "未选中"));
    }

    public void CheckBox2_CheckedChanged(Object sender, EventArgs e) {
        labResult.setText("【服务端】复选框2的状态：" + (CheckBox2.isChecked() ? "选中" : "未选中"));
    }
}
