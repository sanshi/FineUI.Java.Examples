package com.fineui.java.examples.iframe.passvalue;

import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.RadioButtonList;
import com.fineui.java.examples.code.DataSourceUtil;
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
            ddlSheng.setDataSource(DataSourceUtil.SHENG);
            ddlSheng.dataBind();
            String selected = getQueryParam("selected");
            if (selected != null && !selected.isEmpty()) {
                ddlSheng.setSelectedValue(selected);
            }
        }
    }

    public void ddlSheng_SelectedIndexChanged(Object sender, EventArgs e) {
        ActiveWindow.writeBackValue(ddlSheng.getSelectedValue());
        ActiveWindow.hide();
    }
}
