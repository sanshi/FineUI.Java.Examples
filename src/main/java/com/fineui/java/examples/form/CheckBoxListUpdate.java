package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.CheckBoxList;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 复选框列表 - 运行时更新演示页（路由 {@code form/check-box-list-update}）：在回发事件里重新绑定列表项与选中项
 * （运行时重新绑定数据）。运行时改动经增量下发、客户端 {@code loadData} 重建；列表四可反复"清空/填充"切换。
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

    // 数据绑定列表二（value1..6 → 可选项 X），选中 value1/value2/value3
    private void loadData() {
        rebind(CheckBoxList2, 6, "可选项 ", List.of("value1", "value2", "value3"));
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
        rebind(CheckBoxList1, 4, "数据绑定值 ", List.of("value1", "value3"));
    }

    public void btnUpdateList2_Click(Object sender, EventArgs e) {
        rebind(CheckBoxList2, 6, "数据绑定值 ", List.of("value1", "value3", "value6"));
    }

    public void btnUpdateList3_Click(Object sender, EventArgs e) {
        rebind(CheckBoxList3, 9, "数据绑定值 ", List.of("value1", "value2", "value6", "value7"));
    }

    public void btnUpdateList4_Click(Object sender, EventArgs e) {
        if (CheckBoxList4.getItemCount() > 0) {
            CheckBoxList4.clearItems();
        } else {
            rebind(CheckBoxList4, 6, "数据绑定值 ", List.of("value1", "value2"));
        }
    }

    // 清空后按 valueN → 前缀+N 重建 count 项，并设置选中值
    private static void rebind(CheckBoxList list, int count, String textPrefix, List<String> selected) {
        list.clearItems();
        for (int i = 1; i <= count; i++) {
            list.addCheckItem("value" + i, textPrefix + i, true, false);
        }
        list.setSelectedValues(selected);
    }
}
