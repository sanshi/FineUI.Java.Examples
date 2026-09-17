package com.fineui.java.examples.iframe.gridiframereload;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;

/**
 * 子页：更新父页面表格（不关闭窗体，路由 {@code iframe/grid-iframe-reload/iframe-window}）。
 * 「更新父页面表格」按钮回发后调用父页脚本 {@code closeWindow1(参数)} -> {@code F.customEvent('CloseWindow1')}
 * 回发父页，窗体保持打开（reload 而非 close）。全程无 eval：服务端只下发「调用激活窗体所在页面的某个
 * 全局函数」这条命令，由客户端按名解析执行。
 */
@FineUIPage("iframe/grid-iframe-reload/iframe-window")
public class IFrameWindow extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnUpdateParentGrid_Click(Object sender, EventArgs e) {
        // 1. 这里放置保存窗体中数据的逻辑
        // 2. 不关闭窗体，直接回发父窗体
        invokeActiveWindowFunction("closeWindow1", "参数 - " + LocalTime.now().getNano() / 1_000_000);
    }
}
