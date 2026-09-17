package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 合计行（内存分页，整表合计，路由 {@code grid-data-url/paging-summary}）：
 * 表格从 {@code data-url} 拉取 {@code {data, summaryData}}，整表合计随首次响应带回，客户端分页时合计行固定不变。
 */
@FineUIPage("grid-data-url/paging-summary")
public class PagingSummary extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 数据与整表合计均由客户端向 data-url 拉取，服务端不绑定。
    }
}
