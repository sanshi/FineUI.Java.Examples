package com.fineui.java.examples.form;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 复选框列表 - 至少选中一项演示页（路由 {@code form/check-box-list-radio-at-least-one}）：通过客户端 {@code change}
 * 监听强制"至少选中一项"——取消最后一个选中项时自动恢复选中，并经自定义事件回发通知当前选中项。
 */
@FineUIPage("form/check-box-list-radio-at-least-one")
public class CheckBoxListRadioAtLeastOne extends PageBase {

    com.fineui.java.core.controls.CheckBoxList CheckBoxList1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("CheckBoxList1_Change".equals(e.getEventName())) {
            if (!CheckBoxList1.getSelectedValues().isEmpty()) {
                showNotify("列表一的选中项：" + String.join(", ", CheckBoxList1.getSelectedValues()));
            } else {
                showNotify("列表一没有选中项！");
            }
        }
    }
}
