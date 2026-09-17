package com.fineui.java.examples.toolbar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.examples.code.PageBase;

/**
 * 工具栏填充演示页（路由 {@code toolbar/toolbar-fill}）：工具栏中用 ToolbarFill 弹性空白把按钮推到两端；
 * 底部按钮在服务端切换某个按钮与填充块的显示/隐藏，观察布局的弹性变化。
 */
@FineUIPage("toolbar/toolbar-fill")
public class ToolbarFill extends PageBase {

    Button Button1;
    com.fineui.java.core.controls.ToolbarFill ToolbarFill1;
    com.fineui.java.core.controls.ToolbarFill ToolbarFill5;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 切换按钮一与其后填充块的显示/隐藏。 */
    public void btnHideFill1_Click(Object sender, EventArgs e) {
        boolean hidden = Button1.isHidden();
        Button1.setHidden(!hidden);
        ToolbarFill1.setHidden(!hidden);
    }

    /** 切换按钮七后面填充块的显示/隐藏。 */
    public void btnHideFill2_Click(Object sender, EventArgs e) {
        ToolbarFill5.setHidden(!ToolbarFill5.isHidden());
    }
}
