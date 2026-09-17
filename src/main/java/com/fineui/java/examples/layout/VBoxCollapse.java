package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * VBox 折叠布局演示页（路由 {@code layout/vbox-collapse}）：外层面板按 VBox 纵向排列三个可折叠子面板，
 * 各子面板均带标题栏与折叠按钮，折叠某个子面板时其余子面板按 BoxFlex 重新分配纵向空间。
 */
@FineUIPage("layout/vbox-collapse")
public class VBoxCollapse extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
