package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 树表格延迟加载（路由 {@code grid-data-url/tree-grid-lazy-load}）：树表格由 {@code data-url} 按需拉取，
 * 展开节点时携带 {@code lazyrowid} 请求其子节点；客户端 {@code onGrid1RowDataBound} 把「文件夹」类型标记为非叶子节点（可展开）。
 */
@FineUIPage("grid-data-url/tree-grid-lazy-load")
public class TreeGridLazyLoad extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 数据由客户端向 data-url 按需拉取，服务端不绑定。
    }
}
