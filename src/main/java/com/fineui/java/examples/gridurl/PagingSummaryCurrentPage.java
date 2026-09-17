package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 合计行（内存分页，当前页合计，路由 {@code grid-data-url/paging-summary-current-page}）：
 * 表格从 {@code data-url} 拉取纯数组，切页时 {@code dataload} 客户端回调按当前页数据重算 Fee/ExtraFee 合计并刷新合计行。
 */
@FineUIPage("grid-data-url/paging-summary-current-page")
public class PagingSummaryCurrentPage extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 数据由客户端拉取，当前页合计在客户端 dataload 回调里计算，服务端不绑定。
    }
}
