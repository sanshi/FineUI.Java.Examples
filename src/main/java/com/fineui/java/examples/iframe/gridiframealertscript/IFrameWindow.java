package com.fineui.java.examples.iframe.gridiframealertscript;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;

/**
 * 子页：更新父页面表格（脚本路径，路由 {@code iframe/grid-iframe-alert-script/iframe-window}）。
 * 「更新父页面表格」按钮在顶层弹「保存成功！」，点确定后隐藏窗体并调用父页脚本函数
 * {@code closeWindow1(参数)} -> {@code F.customEvent('CloseWindow1')} 回发父页
 * （区别于 Alert 版的 {@code HidePostBack} 走 {@code OnClose}）。
 */
@FineUIPage("iframe/grid-iframe-alert-script/iframe-window")
public class IFrameWindow extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnUpdateParentGrid_Click(Object sender, EventArgs e) {
        // 1. 这里放置保存窗体中数据的逻辑
        // 2. 先弹出提示对话框，点确定后隐藏窗体、调用父页脚本 closeWindow1(参数) 回发
        //    （无 eval：按名调用父页函数，实现「关闭后执行脚本」的效果）
        showAlertInTopHideCallParentFn("保存成功！", "", MessageBoxIcon.Success,
                "closeWindow1", "参数 - " + LocalTime.now().getNano() / 1_000_000);
    }
}
