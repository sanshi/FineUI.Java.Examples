package com.fineui.java.examples.iframe.passvalue;

import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.RadioButtonList;
import com.fineui.java.examples.code.ProvinceCityCountyData;
import com.fineui.java.examples.code.PageBase;

/**
 * 子页：选择省份（服务端回写，路由 {@code iframe/pass-value/iframe-window}）。省份单选列表选择经服务端
 * {@code OnSelectedIndexChanged} -> {@code writeBackValue(选中值)} 回写父页 {@code tbxProvince} 并隐藏窗体
 * （无 eval：用结构化命令回写值并隐藏窗体）。
 */
@FineUIPage("iframe/pass-value/iframe-window")
public class IFrameWindow extends PageBase {

    RadioButtonList ddlSheng;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            ProvinceCityCountyData.bind(ddlSheng, ProvinceCityCountyData.provinces());
            String selected = getQueryValue("selected");
            if (!selected.isEmpty()) {
                ddlSheng.setSelectedValue(ProvinceCityCountyData.provinceCode(selected));
            }
        }
    }

    public void ddlSheng_SelectedIndexChanged(Object sender, EventArgs e) {
        ActiveWindow.writeBackValue(ProvinceCityCountyData.provinceName(ddlSheng.getSelectedValue()));
        ActiveWindow.hide();
    }
}
