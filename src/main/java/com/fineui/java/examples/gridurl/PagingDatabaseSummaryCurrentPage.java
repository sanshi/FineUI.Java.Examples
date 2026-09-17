package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 合计行（数据库分页，当前页合计，路由 {@code grid-data-url/paging-database-summary-current-page}）：
 * 表格从 {@code data-url} 按页拉取 {@code {recordCount, data, summaryData}}，每页各带本页 Fee/ExtraFee 合计，翻页时合计行随之更新。
 */
@FineUIPage("grid-data-url/paging-database-summary-current-page")
public class PagingDatabaseSummaryCurrentPage extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 数据与当前页合计均按页由服务端接口返回，页面服务端不绑定。
    }
}
