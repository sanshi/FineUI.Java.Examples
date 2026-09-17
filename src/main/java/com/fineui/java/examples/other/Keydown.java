package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 文本框改变客户端事件演示页面模型类（路由 {@code other/keydown}）：TextBox1 的 change 事件纯客户端同步
 * 到 TextBox2（{@code F.ui.TextBox2.setValue(this.getValue())}），不经服务端回发。无服务端事件。
 */
@FineUIPage("other/keydown")
public class Keydown extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
