package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.List;

/** 下拉列表绑定到复合对象列表演示页（路由 {@code drop-down-list/data-bind-composite-list}）：按 dataTextField/dataValueField 取文本与值。 */
@FineUIPage("drop-down-list/data-bind-composite-list")
public class DataBindCompositeList extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    /** 演示用的复合数据项。 */
    public static class Item {
        private final String id;
        private final String name;

        public Item(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            List<Item> list = new ArrayList<>();
            list.add(new Item("1", "可选项1"));
            list.add(new Item("2", "可选项2"));
            list.add(new Item("3", "可选项3"));
            list.add(new Item("4", "可选项4"));
            list.add(new Item("5", "可选项5"));
            list.add(new Item("6", "可选项6"));
            list.add(new Item("7", "可选择项7"));
            list.add(new Item("8", "可选择项8"));
            list.add(new Item("9", "可选择项9"));
            DropDownList1.setDataTextField("name");
            DropDownList1.setDataValueField("id");
            DropDownList1.setDataSource(list);
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
