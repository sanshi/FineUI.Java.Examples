package com.fineui.java.examples.partial;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 分部视图布局（路由 {@code partial/layout}）：用户卡片（分部视图）嵌入 VBox / HBox
 * 复杂布局，卡片按 {@code box-flex} 占位；不同卡片承载不同用户信息。
 */
@FineUIPage("partial/layout")
public class Layout extends PageBase {

    public void Page_Load(Object sender, com.fineui.java.core.EventArgs e) {
    }
}
