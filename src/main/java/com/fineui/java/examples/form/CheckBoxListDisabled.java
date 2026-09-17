package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 复选框列表 - 禁用项演示页（路由 {@code form/check-box-list-disabled}）：声明式 {@code enabled="false"} 与
 * 数据绑定禁用（按数据字段控制每项是否可选）两种方式；禁用项渲染 {@code f-state-disabled} 且不可切换。
 */
@FineUIPage("form/check-box-list-disabled")
public class CheckBoxListDisabled extends PageBase {

    com.fineui.java.core.controls.CheckBoxList CheckBoxList1;
    com.fineui.java.core.controls.CheckBoxList CheckBoxList2;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    // 数据绑定列表二：item1/item2 禁用（按数据字段控制可选性），选中 item1/item3
    private void loadData() {
        CheckBoxList2.addCheckItem("item1", "数据绑定值 1", false, false);
        CheckBoxList2.addCheckItem("item2", "数据绑定值 2", false, false);
        CheckBoxList2.addCheckItem("item3", "数据绑定值 3", true, false);
        CheckBoxList2.addCheckItem("item4", "数据绑定值 4", true, false);
        CheckBoxList2.setSelectedValues(List.of("item1", "item3"));
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.of(CheckBoxList1, CheckBoxList2));
    }

    public void btnServerSetSelectedValue_Click(Object sender, EventArgs e) {
        CheckBoxList1.setSelectedValues(List.of("value1", "value3"));
    }

    public void btnServerGetSelectedValue_Click(Object sender, EventArgs e) {
        if (!CheckBoxList1.getSelectedValues().isEmpty()) {
            showNotify("列表一的选中项：" + String.join(", ", CheckBoxList1.getSelectedValues()));
        } else {
            showNotify("列表一没有选中项！");
        }
    }
}
