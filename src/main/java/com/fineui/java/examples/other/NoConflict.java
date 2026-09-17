package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.enums.IconFont;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 释放 window.F 命名空间演示页面模型类（路由 {@code other/no-conflict}）：页面 head 里先自定义一个
 * 全局 {@code window.F = {message:'Hello world!'}}，FineUI 加载后占用 window.F，页面脚本调用
 * {@code F.noConflict()} 释放占用、还原自定义对象（框架转移到 window.FineUI 命名空间），
 * 并在 {@code FineUI.ready} 回调里用局部 F 访问框架。服务端仅首屏给主按钮设置 IconFont。
 */
@FineUIPage("other/no-conflict")
public class NoConflict extends PageBase {

    Button btnPrimary;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            btnPrimary.setIconFont(IconFont.Tag);
        }
    }

    // 其余按钮仅页面展示；btnChangeEnable/btnEnable/btnChangePressed/btnPressed 均纯客户端或回发提示

    public void btnChangeEnable_Click(Object sender, EventArgs e) {
        btnEnable.setEnabled(true);
        btnEnable.setText("本按钮已经启用（点击弹出对话框）");
    }

    public void btnEnable_Click(Object sender, EventArgs e) {
        showNotify("你点击了刚刚启用的按钮");
    }

    public void btnChangePressed_Click(Object sender, EventArgs e) {
        btnPressed.setPressed(!btnPressed.isPressed());
    }

    Button btnEnable;
    Button btnChangePressed;
    Button btnPressed;
}
