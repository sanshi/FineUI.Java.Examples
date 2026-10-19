package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.CheckBoxList;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 复选框列表 - 运行时更新演示页（路由 {@code form/check-box-list-update}）：在回发事件里重新绑定列表项与选中项
 * （运行时重新绑定数据）。列表四可反复切换清空和填充。
 */
@FineUIPage("form/check-box-list-update")
public class CheckBoxListUpdate extends PageBase {

    CheckBoxList CheckBoxList1;
    CheckBoxList CheckBoxList2;
    CheckBoxList CheckBoxList3;
    CheckBoxList CheckBoxList4;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        List<TestClass> dataSource = List.of(
                new TestClass("value1", "可选项 1"),
                new TestClass("value2", "可选项 2"),
                new TestClass("value3", "可选项 3"),
                new TestClass("value4", "可选项 4"),
                new TestClass("value5", "可选项 5"),
                new TestClass("value6", "可选项 6")
        );
        rebind(CheckBoxList2, dataSource, List.of("value1", "value2", "value3"));
    }

    public void btnCheckedItemsList1_Click(Object sender, EventArgs e) {
        showNotify("列表一的选中项：" + String.join(", ", CheckBoxList1.getSelectedValues()));
    }

    public void btnCheckedItemsList2_Click(Object sender, EventArgs e) {
        showNotify("列表二的选中项：" + String.join(", ", CheckBoxList2.getSelectedValues()));
    }

    public void btnCheckedItemsList3_Click(Object sender, EventArgs e) {
        showNotify("列表三的选中项：" + String.join(", ", CheckBoxList3.getSelectedValues()));
    }

    public void btnCheckedItemsList4_Click(Object sender, EventArgs e) {
        showNotify("列表四的选中项：" + String.join(", ", CheckBoxList4.getSelectedValues()));
    }

    public void btnUpdateList1_Click(Object sender, EventArgs e) {
        List<TestClass> dataSource = List.of(
                new TestClass("value1", "数据绑定值 1"),
                new TestClass("value2", "数据绑定值 2"),
                new TestClass("value3", "数据绑定值 3"),
                new TestClass("value4", "数据绑定值 4")
        );
        rebind(CheckBoxList1, dataSource, List.of("value1", "value3"));
    }

    public void btnUpdateList2_Click(Object sender, EventArgs e) {
        List<TestClass> dataSource = List.of(
                new TestClass("value1", "数据绑定值 1"),
                new TestClass("value2", "数据绑定值 2"),
                new TestClass("value3", "数据绑定值 3"),
                new TestClass("value4", "数据绑定值 4"),
                new TestClass("value5", "数据绑定值 5"),
                new TestClass("value6", "数据绑定值 6")
        );
        rebind(CheckBoxList2, dataSource, List.of("value1", "value3", "value6"));
    }

    public void btnUpdateList3_Click(Object sender, EventArgs e) {
        List<TestClass> dataSource = List.of(
                new TestClass("value1", "数据绑定值 1"),
                new TestClass("value2", "数据绑定值 2"),
                new TestClass("value3", "数据绑定值 3"),
                new TestClass("value4", "数据绑定值 4"),
                new TestClass("value5", "数据绑定值 5"),
                new TestClass("value6", "数据绑定值 6"),
                new TestClass("value7", "数据绑定值 7"),
                new TestClass("value8", "数据绑定值 8"),
                new TestClass("value9", "数据绑定值 9")
        );
        rebind(CheckBoxList3, dataSource, List.of("value1", "value2", "value6", "value7"));
    }

    public void btnUpdateList4_Click(Object sender, EventArgs e) {
        if (CheckBoxList4.getItemCount() > 0) {
            CheckBoxList4.clearItems();
        } else {
            List<TestClass> dataSource = List.of(
                    new TestClass("value1", "数据绑定值 1"),
                    new TestClass("value2", "数据绑定值 2"),
                    new TestClass("value3", "数据绑定值 3"),
                    new TestClass("value4", "数据绑定值 4"),
                    new TestClass("value5", "数据绑定值 5"),
                    new TestClass("value6", "数据绑定值 6")
            );
            rebind(CheckBoxList4, dataSource, List.of("value1", "value2"));
        }
    }

    /** 将每项的名称和编号映射到列表；绑定后用编号设置选中项。 */
    private static void rebind(CheckBoxList list, List<TestClass> dataSource, List<String> selected) {
        list.setDataTextField("name");
        list.setDataValueField("id");
        list.setDataSource(dataSource);
        list.dataBind();
        list.setSelectedValues(selected);
    }

    /** 示例数据中的 id 作为选中值，name 作为显示文本。 */
    public record TestClass(String id, String name) {
    }
}
