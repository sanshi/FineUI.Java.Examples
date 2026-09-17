package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/** 下拉列表多选（标签模式）、可编辑、带初始用户输入值演示页（路由 {@code drop-down-list/multi-select-tags-no-force-selection-init-value}）。 */
@FineUIPage("drop-down-list/multi-select-tags-no-force-selection-init-value")
public class MultiSelectTagsNoForceSelectionInitValue extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 初始选中两个真实项 + 一个用户输入值（__USERINPUT_ 前缀标记非列表项的用户录入值）
            DropDownList1.setText("可选项1, 可选项4, 初始自定义值1");
            DropDownList1.setSelectedValues(List.of("Value1", "Value4", "__USERINPUT_value1"));
        }
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("Value6");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownList1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText("选中项文本：" + text + "<br/>选中项值：" + String.join(", ", DropDownList1.getSelectedValues()));
        } else {
            labResult.setText("无选中项");
        }
    }
}
