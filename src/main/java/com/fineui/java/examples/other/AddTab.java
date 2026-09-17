package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 向父页面添加选项卡演示页面模型类（路由 {@code other/add-tab}）：Button1/Button4/Button2 为纯客户端脚本
 * （调用父框架的 {@code addExampleTab}/{@code removeActiveTab}），Button3 服务端回发后按名调用页面
 * 已定义的全局函数 {@code onCloseActiveTabClick}（无 eval）。
 */
@FineUIPage("other/add-tab")
public class AddTab extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button3_Click(Object sender, EventArgs e) {
        // 按名调用页面 script 槽定义的全局函数（无 eval）
        invokeClientFunction("onCloseActiveTabClick");
    }
}
