package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 弹出 IFrame 窗体（路由 {@code grid-data-url/i-frame}）：数据由 {@code data-url} 客户端拉取，
 * 末列由客户端渲染「编辑」按钮，点击后客户端弹出 IFrame 窗体；窗体关闭时触发服务端 {@code Window1_Close} 事件。
 */
@FineUIPage("grid-data-url/i-frame")
public class IFrame extends PageBase {

    Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
        // 数据由客户端向 data-url 拉取；窗体的显示由客户端脚本驱动。
    }

    public void Window1_Close(Object sender, EventArgs e) {
        showAlert("触发了窗体的关闭事件！");
    }
}
