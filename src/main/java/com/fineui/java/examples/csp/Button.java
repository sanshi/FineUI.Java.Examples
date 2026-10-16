package com.fineui.java.examples.csp;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.enums.IconFont;
import com.fineui.java.examples.code.PageBase;

/**
 * CSP 按钮（路由 {@code csp/button}）：演示开启内容安全策略并启用 nonce（{@code cspScriptsAllowNonce}）。
 *
 * <p>页面开启 CSP 后浏览器只放行同源脚本与带匹配 {@code nonce} 的内联脚本；页面里各按钮的点击回发
 * 与自带的内联 {@code <script>}（带 nonce）均正常运行——证明 FineUI.Java 在严格 CSP 下功能完整，
 * 且无需 {@code 'unsafe-eval'}（纯 JSON 回发、无 eval）。
 */
@FineUIPage("csp/button")
public class Button extends PageBase {

    com.fineui.java.core.controls.Button btnPrimary;
    com.fineui.java.core.controls.Button btnEnable;
    com.fineui.java.core.controls.Button btnPressed;
    com.fineui.java.core.controls.Button btnTooltip;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            btnPrimary.setIconFont(IconFont.Tag);
        }
    }

    public void btnEnable_Click(Object sender, EventArgs e) {
        showNotify("你点击了刚刚启用的按钮");
    }

    public void btnChangeEnable_Click(Object sender, EventArgs e) {
        btnEnable.setEnabled(true);
        btnEnable.setText("本按钮已经启用（点击弹出对话框）");
    }

    public void btnChangePressed_Click(Object sender, EventArgs e) {
        btnPressed.setPressed(!btnPressed.isPressed());
    }

    public void btnTooltip_Click(Object sender, EventArgs e) {
        btnTooltip.setToolTip("这是改变后的提示信息");
    }
}
