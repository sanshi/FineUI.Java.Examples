package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 客户端分页 + 数据库排序示例（路由 {@code grid-data-url/paging-sorting-database}）：
 * 排序在服务端按 sortField/sortDirection 完成后返回，客户端本地分页。数据由 {@code data-url} 按需拉取，服务端无需绑定数据。
 */
@FineUIPage("grid-data-url/paging-sorting-database")
public class PagingSortingDatabase extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 数据由客户端向 data-url 拉取，服务端不绑定。
    }
}
