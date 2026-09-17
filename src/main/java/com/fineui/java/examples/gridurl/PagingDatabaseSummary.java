package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 合计行（数据库分页，整表合计，路由 {@code grid-data-url/paging-database-summary}）：
 * 表格从 {@code data-url} 按页拉取 {@code {recordCount, data, summaryData}}，整表合计仅首页返回一次，翻页时合计行保持不变。
 */
@FineUIPage("grid-data-url/paging-database-summary")
public class PagingDatabaseSummary extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 数据按页拉取、整表合计首页随响应带回，服务端不绑定。
    }
}
