package com.fineui.java.examples.iframe.passvaluescript;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.RadioButtonList;
import com.fineui.java.examples.code.DataSourceUtil;
import com.fineui.java.examples.code.PageBase;

/**
 * 子页：选择省份（纯脚本回写，路由 {@code iframe/pass-value-script/iframe-window}）。省份单选列表的
 * {@code change} 监听器（客户端 {@code onDdlShengChange}）经 {@code F.getActiveWindow().window.updateProvince(value)}
 * 纯脚本回写父页 {@code tbxProvince} 后 {@code activeWindow.hide()}，全程无服务端回发。
 */
@FineUIPage("iframe/pass-value-script/iframe-window")
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
}
