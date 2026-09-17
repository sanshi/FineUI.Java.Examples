package com.fineui.java.examples.gridother;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/** 新标签页编辑表单页（含「关闭并刷新父选项卡」按钮，路由 {@code grid-other/new-tab-hide-refresh-window}）：申请人默认「三生石上」，可由 query 参数 name 覆盖。 */
@FineUIPage("grid-other/new-tab-hide-refresh-window")
public class NewTabHideRefreshWindow extends PageBase {

    Label labUserName;

    public void Page_Load(Object sender, EventArgs e) {
        String name = getQueryParam("name");
        if (name != null && !name.isEmpty()) {
            labUserName.setText(name);
        }
    }
}
