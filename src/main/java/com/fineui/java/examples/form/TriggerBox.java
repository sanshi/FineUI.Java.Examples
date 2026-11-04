package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.PageBase;

/**
 * 触发按钮文本框演示页（路由 {@code form/trigger-box}）：点击输入框（enable-click-action）触发 triggerclick
 * 客户端事件弹出模态窗口；窗口内按钮点击回发、服务端关闭窗口并回填文本。
 */
@FineUIPage("form/trigger-box")
public class TriggerBox extends PageBase {

    Window Window1;
    com.fineui.java.core.controls.TriggerBox TriggerBox1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnCloseWindow_Click(Object sender, EventArgs e) {
        Window1.setHidden(true);
        TriggerBox1.setValue("弹出窗口被关闭了");
    }
}
