package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 单选按钮列表 - 禁用项演示页（路由 {@code form/radio-button-list-disabled}）：声明式 {@code enabled="false"} 与
 * 数据绑定禁用（按数据字段控制每项是否可选）两种方式；禁用项渲染 {@code f-state-disabled} 且不可选中。
 */
@FineUIPage("form/radio-button-list-disabled")
public class RadioButtonListDisabled extends PageBase {

    com.fineui.java.core.controls.RadioButtonList RadioButtonList1;
    com.fineui.java.core.controls.RadioButtonList RadioButtonList2;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    // 数据绑定列表二：item1/item2 禁用（按数据字段控制可选性），选中 item3
    private void loadData() {
        RadioButtonList2.addRadioItem("item1", "数据绑定值 1", false, false);
        RadioButtonList2.addRadioItem("item2", "数据绑定值 2", false, false);
        RadioButtonList2.addRadioItem("item3", "数据绑定值 3", true, false);
        RadioButtonList2.addRadioItem("item4", "数据绑定值 4", true, false);
        RadioButtonList2.setSelectedValue("item3");
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.of(RadioButtonList1, RadioButtonList2));
    }

    public void btnServerGetSelectedValue_Click(Object sender, EventArgs e) {
        if (RadioButtonList1.getSelectedValue() != null) {
            showNotify("列表一的选中项：" + RadioButtonList1.getSelectedValue());
        } else {
            showNotify("列表一没有选中项！");
        }
    }

    public void btnServerSetSelectedValue_Click(Object sender, EventArgs e) {
        RadioButtonList1.setSelectedValue("value1");
    }
}
