package com.fineui.java.examples.mobile.button;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 移动端按钮分组与选项卡交互演示页（路由 {@code mobile/button/button-group-tab-strip}）：
 * 顶部按下互斥分组的按钮切换下方隐藏了选项卡头的选项卡内容，切换在客户端完成（{@code presschange} → 激活对应选项卡），无回发。
 */
@FineUIPage("mobile/button/button-group-tab-strip")
public class ButtonGroupTabStrip extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
