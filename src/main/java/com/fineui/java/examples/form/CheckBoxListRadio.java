package com.fineui.java.examples.form;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 复选框列表 - 最多选中一项演示页（路由 {@code form/check-box-list-radio}）：通过客户端 {@code change} 监听把
 * 复选框列表变成"类单选"（选中新项时仅保留该项），并经自定义事件回发通知当前选中项。
 */
@FineUIPage("form/check-box-list-radio")
public class CheckBoxListRadio extends PageBase {

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
