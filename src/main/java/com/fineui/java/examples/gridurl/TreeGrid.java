package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 树表格（路由 {@code grid-data-url/tree-grid}）：开启树形表格，名称列为树列，
 * 由 {@code data-id-field}/{@code data-parent-id-field} 按父子关系拼成层级。整棵树由 {@code data-url} 一次拉取。
 */
@FineUIPage("grid-data-url/tree-grid")
public class TreeGrid extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 整棵树由客户端向 data-url 一次性拉取，服务端不绑定。
    }
}
