package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/** 下拉列表绑定到简单字符串列表演示页（路由 {@code drop-down-list/data-bind-simple-list}）。 */
@FineUIPage("drop-down-list/data-bind-simple-list")
public class DataBindSimpleList extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            DropDownList1.setDataSource(List.of(
                    "可选项1", "可选项2", "可选项3", "可选项4", "可选项5", "可选项6",
                    "可选择项7", "可选择项8", "可选择项9",
                    "这是一个很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的可选项"));
            DropDownList1.dataBind();
        }
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("可选项6");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownList1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText("选中项：" + text + "（值：" + DropDownList1.getSelectedValue() + "）");
        } else {
            labResult.setText("无选中项");
        }
    }
}
