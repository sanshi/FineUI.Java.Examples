package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 简单下拉列表演示页（路由 {@code drop-down-list/drop-down-list}）：一个下拉列表（含不可选项），
 * 「选中[可选项6]」在服务端把选中值设为 Value6，「获取此下拉列表的选中项」回发读取当前选中项的文本与值并回填标签。
 */
@FineUIPage("drop-down-list/drop-down-list")
public class DropDownList extends PageBase {

    // 页面类名与控件类同名，控件字段用全限定名消歧。
    com.fineui.java.core.controls.DropDownList DropDownList1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("Value6");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownList1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("选中项：%s（值：%s）", text, DropDownList1.getSelectedValue()));
        } else {
            labResult.setText("无选中项");
        }
    }
}
