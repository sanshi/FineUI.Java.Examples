package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 侧边/顶部/底部窗体示例（路由 {@code window/side}）：四个贴边窗体（左/右占满高度、顶/底占满宽度，
 * 均为模态且点遮罩关闭），加一个居中窗体放置四个按钮分别弹出对应贴边窗体。
 */
@FineUIPage("window/side")
public class Side extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
