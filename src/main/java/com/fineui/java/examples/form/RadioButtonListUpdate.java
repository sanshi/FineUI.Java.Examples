package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.RadioButtonList;
import com.fineui.java.examples.code.PageBase;

/**
 * 单选按钮列表 - 运行时更新演示页（路由 {@code form/radio-button-list-update}）：在回发事件里重新绑定列表项与选中项
 * （运行时重新绑定数据）。运行时改动经增量下发、客户端 {@code loadData} 重建；列表四可反复"清空/填充"切换。
 */
@FineUIPage("form/radio-button-list-update")
public class RadioButtonListUpdate extends PageBase {

    RadioButtonList RadioButtonList1;
    RadioButtonList RadioButtonList2;
    RadioButtonList RadioButtonList3;
    RadioButtonList RadioButtonList4;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    // 数据绑定列表二（value1..6 → 可选项 X），选中 value2
    private void loadData() {
        rebind(RadioButtonList2, 6, "可选项 ", "value2");
    }

    public void btnCheckedItemsList1_Click(Object sender, EventArgs e) {
        showNotify("列表一的选中项：" + nullToEmpty(RadioButtonList1.getSelectedValue()));
    }

    public void btnCheckedItemsList2_Click(Object sender, EventArgs e) {
        showNotify("列表二的选中项：" + nullToEmpty(RadioButtonList2.getSelectedValue()));
    }

    public void btnCheckedItemsList3_Click(Object sender, EventArgs e) {
        showNotify("列表三的选中项：" + nullToEmpty(RadioButtonList3.getSelectedValue()));
    }

    public void btnCheckedItemsList4_Click(Object sender, EventArgs e) {
        showNotify("列表四的选中项：" + nullToEmpty(RadioButtonList4.getSelectedValue()));
    }

    public void btnUpdateList1_Click(Object sender, EventArgs e) {
        rebind(RadioButtonList1, 4, "数据绑定值 ", "value1");
    }

    public void btnUpdateList2_Click(Object sender, EventArgs e) {
        rebind(RadioButtonList2, 6, "数据绑定值 ", "value1");
    }

    public void btnUpdateList3_Click(Object sender, EventArgs e) {
        rebind(RadioButtonList3, 9, "数据绑定值 ", "value3");
    }

    public void btnUpdateList4_Click(Object sender, EventArgs e) {
        if (RadioButtonList4.getItemCount() > 0) {
            RadioButtonList4.clearItems();
        } else {
            rebind(RadioButtonList4, 6, "数据绑定值 ", "value2");
        }
    }

    private static void rebind(RadioButtonList list, int count, String textPrefix, String selected) {
        list.clearItems();
        for (int i = 1; i <= count; i++) {
            list.addRadioItem("value" + i, textPrefix + i, true, false);
        }
        list.setSelectedValue(selected);
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}
