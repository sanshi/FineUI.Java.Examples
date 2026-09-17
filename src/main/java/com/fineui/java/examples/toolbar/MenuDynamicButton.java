package com.fineui.java.examples.toolbar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Toolbar;
import com.fineui.java.examples.code.PageBase;

/**
 * 向工具栏中动态添加按钮页（路由 {@code toolbar/menu-dynamic-button}）：工具栏里有一个模板声明的内联按钮，
 * 服务端再动态添加一个按钮（带服务端点击事件），点击后提示工具栏当前按钮数。
 *
 * <p>动态按钮仅首屏创建（{@code if (!isPostBack())}）：回发时客户端把该按钮随 {@code __FSTATE} 回带、服务端据此
 * 重建控件树，故它已在树内、点击事件能路由到处理器（用固定 id 保证回发目标匹配）；若回发时再加一次会重复。
 */
@FineUIPage("toolbar/menu-dynamic-button")
public class MenuDynamicButton extends PageBase {

    Toolbar Toolbar1;
    Button btnDynamic;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        } else {
            // 动态控件的服务端事件映射不信任 __FSTATE，回发时必须由服务端重新声明。
            btnDynamic.setOnClick("btnDynamic_Click");
        }
    }

    private void loadData() {
        Button btn = new Button();
        btn.setId("btnDynamic");
        btn.setText("工具栏中的按钮数（动态添加的按钮）");
        btn.setOnClick("btnDynamic_Click");   // 服务端点击事件
        Toolbar1.addChild(btn);
    }

    /** 动态按钮点击：提示工具栏当前按钮数（模板内联按钮 + 动态按钮 = 2）。 */
    public void btnDynamic_Click(Object sender, EventArgs e) {
        showNotify("工具栏中的按钮数：" + Toolbar1.childrenIn("items").size());
    }
}
