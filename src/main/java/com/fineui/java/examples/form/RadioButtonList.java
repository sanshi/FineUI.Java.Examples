package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 单选按钮列表演示页（路由 {@code form/radio-button-list}）：单选语义、服务端设置/获取选中项、客户端脚本获取（alert）、
 * 数据绑定初值、选中项改变事件（{@code OnSelectedIndexChanged} → 客户端 change 回发）。
 *
 * <p>页面类名 RadioButtonList 与控件类同名，控件字段用全限定名消歧。
 */
@FineUIPage("form/radio-button-list")
public class RadioButtonList extends PageBase {

    com.fineui.java.core.controls.RadioButtonList RadioButtonList1;
    com.fineui.java.core.controls.RadioButtonList RadioButtonList2;
    com.fineui.java.core.controls.RadioButtonList rblAutoPostBack;
    com.fineui.java.core.controls.RadioButtonList RadioButtonList4;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    // 数据绑定列表二（item1..4 → 数据绑定值 X），选中 item3
    private void loadData() {
        RadioButtonList2.addRadioItem("item1", "数据绑定值 1", true, false);
        RadioButtonList2.addRadioItem("item2", "数据绑定值 2", true, false);
        RadioButtonList2.addRadioItem("item3", "数据绑定值 3", true, false);
        RadioButtonList2.addRadioItem("item4", "数据绑定值 4", true, false);
        RadioButtonList2.setSelectedValue("item3");
    }

    public void btnServerSetSelectedValue_Click(Object sender, EventArgs e) {
        RadioButtonList1.setSelectedValue("value1");
    }

    public void btnServerGetSelectedValue_Click(Object sender, EventArgs e) {
        if (RadioButtonList1.getSelectedValue() != null) {
            showNotify("列表一的选中项：" + RadioButtonList1.getSelectedValue());
        } else {
            showNotify("列表一没有选中项！");
        }
    }

    public void rblAutoPostBack_SelectedIndexChanged(Object sender, EventArgs e) {
        showNotify("列表三的选中项：" + rblAutoPostBack.getSelectedValue());
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.of(RadioButtonList1, RadioButtonList2, rblAutoPostBack, RadioButtonList4));
    }
}
