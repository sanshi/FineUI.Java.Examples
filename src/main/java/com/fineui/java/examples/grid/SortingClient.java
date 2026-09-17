package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 排序（客户端排序，路由 {@code grid/sorting-client}）：不定义 {@code onSort} 服务端事件，排序完全在浏览器端完成。
 * 服务端仅首次加载时按初始排序字段绑定一次数据。
 */
@FineUIPage("grid/sorting-client")
public class SortingClient extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.sortedRows(Grid1.getSortField(), Grid1.getSortDirection()));
            Grid1.dataBind();
        }
    }
}
