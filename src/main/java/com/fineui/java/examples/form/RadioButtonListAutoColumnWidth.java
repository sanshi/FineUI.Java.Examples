package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 单选按钮列表 - 自适应列宽演示页（路由 {@code form/radio-button-list-auto-column-width}）：
 * {@code AutoColumnWidth=true} 时各项按内容自适应宽度；服务端设置/获取、选中项改变事件功能与普通单选列表一致。
 */
@FineUIPage("form/radio-button-list-auto-column-width")
public class RadioButtonListAutoColumnWidth extends PageBase {

    com.fineui.java.core.controls.RadioButtonList RadioButtonList1;
    com.fineui.java.core.controls.RadioButtonList RadioButtonList2;
    com.fineui.java.core.controls.RadioButtonList rblAutoPostBack;
    com.fineui.java.core.controls.RadioButtonList RadioButtonList4;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

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
