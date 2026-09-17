package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 关闭前 F.confirm（路由 {@code tab-strip/beforehide-f-confirm}）：标签三监听客户端 {@code beforehide}，
 * 用异步 {@code F.confirm} 弹确认框；先返回 false 阻止默认关闭，确认后在 {@code F.noEvent} 中隐藏选项卡。
 */
@FineUIPage("tab-strip/beforehide-f-confirm")
public class BeforehideFConfirm extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
