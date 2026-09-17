package com.fineui.java.examples.partial;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 分部视图（路由 {@code partial/partial}）：同一张用户卡片（分部视图）在三种面板结构中
 * 各渲染一次——直接嵌入、嵌套面板、自适应布局（Layout=Fit）；卡片为静态内容，
 * 多次渲染时以不同的控件 id 区分（PartialView0/1/2_*）。
 */
@FineUIPage("partial/partial")
public class Partial extends PageBase {

    public void Page_Load(Object sender, com.fineui.java.core.EventArgs e) {
    }
}
