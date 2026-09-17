package com.fineui.java.examples.tabstrip;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Tab;
import com.fineui.java.examples.code.PageBase;

/**
 * IFrame 选项卡（路由 {@code tab-strip/iframe}）：三个选项卡，后两个用 IFrame 承载外部页面，
 * IFrame 加载完毕时右下角弹提示；按钮在服务端切换第二个选项卡的内联网址。
 */
@FineUIPage("tab-strip/iframe")
public class IFrame extends PageBase {

    Tab Tab2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 切换标签二的内联网址（在两个地址之间来回切换）。 */
    public void Button1_Click(Object sender, EventArgs e) {
        // 依据当前 IFrame 地址在两个内联网址间来回切换，并同步 data-source-key 标记。
        String url = Tab2.getIFrameUrl();
        if (url == null || url.isEmpty() || url.endsWith("/panel/tools")) {
            Tab2.setAttribute("data-source-key", "source1");
            Tab2.setIFrameUrl("/panel/group");
        } else {
            Tab2.setAttribute("data-source-key", "source2");
            Tab2.setIFrameUrl("/panel/tools");
        }
    }
}
