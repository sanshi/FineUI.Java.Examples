package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 跨 IFrame 传值（纯脚本版，路由 {@code iframe/pass-value-script}）：父页用客户端 {@code onButton1Click}
 * 打开窗体（不回发）；子页 {@code RadioButtonList} 的 {@code change} 监听器经
 * {@code F.getActiveWindow().window.updateProvince(value)} 纯脚本回写父页 {@code tbxProvince} 后
 * {@code activeWindow.hide()}，全程无服务端回发（区别于 {@link PassValue} 的服务端
 * SaveState/WriteBackValue 回写）。
 */
@FineUIPage("iframe/pass-value-script")
public class PassValueScript extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Window1_Close(Object sender, EventArgs e) {
        showNotify("触发了 Window1 的关闭事件！");
    }
}
