package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * IFrame 窗体（路由 {@code iframe/window}）：子页 {@code IFrameWindow} 启用了表单改变确认
 * （{@code getPageManager().enableFormChangeConfirm(true)}）——编辑表单后再关闭窗体/选项卡/浏览器
 * 会弹出「确认放弃修改」保护（底层 {@code $(window).on('beforeunload')}，在「确认开关 + 允许关闭前
 * 提示 + 表单已改」三者同时成立时返回 {@code formChangeConfirmMsg}）。
 */
@FineUIPage("iframe/window")
public class Window extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public String getLoadTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
