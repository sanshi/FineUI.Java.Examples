package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Component;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * Hidden 客户端状态回发验证页（路由 {@code button/hidden-client-state}）。
 *
 * <p>覆盖三种基线：模板初始隐藏、默认可见，以及服务端运行时设为隐藏。客户端对它们调用
 * {@code show()/hide()} 后，下一次回发中都应由当前浏览器状态覆盖模板/服务端基线。
 */
@FineUIPage("button/hidden-client-state")
public class HiddenClientState extends PageBase {

    Button TargetInitiallyHidden;
    Button TargetInitiallyVisible;
    Button TargetServerChanged;
    Label TargetOverride;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnHideOnServer_Click(Object sender, EventArgs e) {
        TargetServerChanged.setHidden(true);
    }

    public void btnReadOnServer_Click(Object sender, EventArgs e) {
        showNotify(
                "初始隐藏按钮：" + visibleText(TargetInitiallyHidden)
                        + "；初始显示按钮：" + visibleText(TargetInitiallyVisible)
                        + "；服务端改动按钮：" + visibleText(TargetServerChanged)
                        + "；重写导出控件：" + visibleText(TargetOverride));
    }

    private String visibleText(Component component) {
        return component.isHidden() ? "隐藏" : "显示";
    }
}
