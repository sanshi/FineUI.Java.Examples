package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 复选框列表演示页（路由 {@code form/check-box-list}）：初始选中、服务端设置/获取选中项、客户端脚本获取（alert）、
 * 数据绑定列表初值、选中项改变事件（{@code OnSelectedIndexChanged} → 客户端 change 回发）。
 *
 * <p>页面类名 CheckBoxList 与控件类同名，控件字段用全限定名消歧。
 */
@FineUIPage("form/check-box-list")
public class CheckBoxList extends PageBase {

    com.fineui.java.core.controls.CheckBoxList CheckBoxList1;
    com.fineui.java.core.controls.CheckBoxList CheckBoxList2;
    com.fineui.java.core.controls.CheckBoxList CheckBoxList3;
    com.fineui.java.core.controls.CheckBoxList CheckBoxList4;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    // 数据绑定列表二：运行时逐项加入并预选
    private void loadData() {
        CheckBoxList2.addCheckItem("item1", "数据绑定值 1", true, false);
        CheckBoxList2.addCheckItem("item2", "数据绑定值 2", true, false);
        CheckBoxList2.addCheckItem("item3", "数据绑定值 3", true, false);
        CheckBoxList2.addCheckItem("item4", "数据绑定值 4", true, false);
        CheckBoxList2.setSelectedValues(List.of("item1", "item3"));
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.of(CheckBoxList1, CheckBoxList2, CheckBoxList3, CheckBoxList4));
    }

    public void CheckBoxList3_SelectedIndexChanged(Object sender, EventArgs e) {
        showNotify("列表三的选中项：" + String.join(", ", CheckBoxList3.getSelectedValues()));
    }

    public void btnServerSetSelectedValue_Click(Object sender, EventArgs e) {
        CheckBoxList1.setSelectedValues(List.of("value1", "value3"));
    }

    public void btnServerGetSelectedValue_Click(Object sender, EventArgs e) {
        if (!CheckBoxList1.getSelectedValues().isEmpty()) {
            showNotify("列表一的选中项：" + String.join(", ", CheckBoxList1.getSelectedValues()));
        } else {
            showNotify("列表一没有选中项！");
        }
    }
}
