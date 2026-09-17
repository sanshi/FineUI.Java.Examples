package com.fineui.java.examples.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.examples.code.PageBase;

/**
 * 按钮点击事件演示页（路由 {@code button/button-click}）：服务器端事件（回发）、
 * 客户端事件（标签里用 {@code click-handler} 填页面脚本区的具名函数名）、
 * 以及在回发中改变某个按钮的客户端点击回调。
 */
@FineUIPage("button/button-click")
public class ButtonClick extends PageBase {

    Button btnClientClick2;

    public void btnServerClick_Click(Object sender, EventArgs e) {
        showNotify("这是服务器端事件");
    }

    public void btnChangeClientClick2_Click(Object sender, EventArgs e) {
        // 回发中换掉客户端回调：下发的也只是新函数名，不是脚本
        btnClientClick2.setClickHandler("onChangedClick");
    }
}
