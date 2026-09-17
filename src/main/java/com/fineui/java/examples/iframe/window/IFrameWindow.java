package com.fineui.java.examples.iframe.window;

import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Panel;
import com.fineui.java.examples.code.PageBase;

/**
 * 子页：表单改变确认（路由 {@code iframe/window/iframe-window}）。启用了表单改变确认
 * （{@code getPageManager().enableFormChangeConfirm(true)}）：编辑表单后再关闭窗体/选项卡/浏览器
 * 会弹出「确认放弃修改」保护（底层 {@code $(window).on('beforeunload')}）。
 * 「保存数据」按钮经 {@code ActiveWindow.hide()} 隐藏窗体。
 */
@FineUIPage("iframe/window/iframe-window")
public class IFrameWindow extends PageBase {

    Panel Panel1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 表单改变确认属页面级配置：只首屏下发（对齐约定：页面配置须放 if(!isPostBack()) 内）
            getPageManager().enableFormChangeConfirm(true);
        }
    }

    public void btnClosePostBack_Click(Object sender, EventArgs e) {
        // 1. 首先保存数据
        // 2. 然后关闭本窗体（直接隐藏）
        ActiveWindow.hide();
    }
}
