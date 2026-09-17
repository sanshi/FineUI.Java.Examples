package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 客户端排序 + 分页（路由 {@code grid/sorting-client-paging}）：不定义服务端排序事件即为客户端排序，
 * 客户端在当前已加载数据上排序与分页。
 */
@FineUIPage("grid/sorting-client-paging")
public class SortingClientPaging extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 客户端分页：一次性加载全部数据（按初始排序字段排好序），翻页/排序都在客户端完成
            Grid1.setDataSource(StudentGridData2.pagedSorted(0, 999, Grid1.getSortField(), Grid1.getSortDirection()));
            Grid1.dataBind();
        }
    }
}
