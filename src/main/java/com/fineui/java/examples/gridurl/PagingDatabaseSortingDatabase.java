package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 数据库分页 + 数据库排序示例（路由 {@code grid-data-url/paging-database-sorting-database}）：
 * 分页与排序都在服务端完成，接口返回 {@code {recordCount, data}}。数据由 {@code data-url} 按需拉取，服务端无需绑定数据。
 */
@FineUIPage("grid-data-url/paging-database-sorting-database")
public class PagingDatabaseSortingDatabase extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 数据由客户端向 data-url 拉取，服务端不绑定。
    }
}
