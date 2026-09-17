package com.fineui.java.examples.form;

import com.fineui.java.core.CheckItemEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 复选框列表 - 列表项属性演示页（路由 {@code form/check-box-list-item-data-bound}）：给每个列表项动态设置属性
 * （本例 {@code data-qtip} 提示信息）。列表一在模板声明项、按索引补属性；列表二走数据绑定 + ItemDataBound 事件逐项设置属性并预选。
 */
@FineUIPage("form/check-box-list-item-data-bound")
public class CheckBoxListItemDataBound extends PageBase {

    com.fineui.java.core.controls.CheckBoxList CheckBoxList1;
    com.fineui.java.core.controls.CheckBoxList CheckBoxList2;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 手工设置复选框列表项的提示信息（列表一的项在模板声明，此处按索引补属性）
            CheckBoxList1.setItemAttribute(0, "data-qtip", "这是第一个选项的提示信息");
            CheckBoxList1.setItemAttribute(1, "data-qtip", "这是第二个选项的提示信息");
            CheckBoxList1.setItemAttribute(2, "data-qtip", "这是第三个选项的提示信息");

            loadData();
        }
    }

    // 数据绑定列表二：数据源 + ItemDataBound 事件逐项设置提示信息，再预选 item1/item3
    private void loadData() {
        List<TestClass> myList = List.of(
                new TestClass("item1", "数据绑定值 1"),
                new TestClass("item2", "数据绑定值 2"),
                new TestClass("item3", "数据绑定值 3"),
                new TestClass("item4", "数据绑定值 4")
        );

        CheckBoxList2.setDataTextField("name");
        CheckBoxList2.setDataValueField("id");
        CheckBoxList2.setDataSource(myList);
        CheckBoxList2.dataBind();

        CheckBoxList2.setSelectedValues(List.of("item1", "item3"));   // 首屏预选（对齐初始选中态）
    }

    public void CheckBoxList2_ItemDataBound(Object sender, CheckItemEventArgs e) {
        TestClass dataItem = (TestClass) e.getDataItem();

        e.getItem().getAttributes().put("data-qtip", dataItem.name() + "（值：" + dataItem.id() + "）");
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.of(CheckBoxList1, CheckBoxList2));
    }

    /** 演示数据类：Id 为值字段、Name 为文本字段。 */
    public record TestClass(String id, String name) {
    }
}
