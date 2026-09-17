package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 下拉列表绑定到数据表（行集合）演示页（路由 {@code drop-down-list/data-bind-data-table}）。 */
@FineUIPage("drop-down-list/data-bind-data-table")
public class DataBindDataTable extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            String[] names = {"可选项1", "可选项2", "可选项3", "可选项4", "可选项5", "可选项6",
                    "可选择项7", "可选择项8", "可选择项9", "这是一个很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的可选项"};
            List<Map<String, Object>> rows = new ArrayList<>();
            for (int i = 0; i < names.length; i++) {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put("MyValue", String.valueOf(i + 1));
                row.put("MyText", names[i]);
                rows.add(row);
            }
            DropDownList1.setDataTextField("MyText");
            DropDownList1.setDataValueField("MyValue");
            DropDownList1.setDataSource(rows);
            DropDownList1.dataBind();
        }
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownList1.setSelectedValue("6");
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
