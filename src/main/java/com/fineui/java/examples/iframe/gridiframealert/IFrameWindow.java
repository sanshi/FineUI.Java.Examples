package com.fineui.java.examples.iframe.gridiframealert;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;

/**
 * 子页：更新父页面表格（路由 {@code iframe/grid-iframe-alert/iframe-window}）。「更新父页面表格」按钮在
 * 顶层弹「保存成功！」，点确定后隐藏窗体并回发父页窗体关闭事件（携带参数「参数 - {毫秒}」），
 * 由父页 {@code Window1_Close} 更新表格标题。
 */
@FineUIPage("iframe/grid-iframe-alert/iframe-window")
public class IFrameWindow extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnUpdateParentGrid_Click(Object sender, EventArgs e) {
        // 1. 这里放置保存窗体中数据的逻辑
        // 2. 先弹出提示对话框，再回发父窗体
        showAlertInTopHidePostBack("保存成功！", "", MessageBoxIcon.Success,
                "参数 - " + LocalTime.now().getNano() / 1_000_000);
    }
}
