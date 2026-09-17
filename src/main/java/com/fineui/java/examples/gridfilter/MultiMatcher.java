package com.fineui.java.examples.gridfilter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumnFilteredItem;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 表头过滤 · 多条件最大条件数（路由 {@code grid-filter/multi-matcher}）：姓名列启用多条件（默认匹配「任一满足 any」），
 * 并把最大条件数从默认 2 改为 5，每条件带操作符（等于/包含/开始于/结束于）。
 */
@FineUIPage("grid-filter/multi-matcher")
public class MultiMatcher extends PageBase {

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            bindGrid();
        }
    }

    public void Grid1_FilterChanged(Object sender, EventArgs e) {
        bindGrid();
        labResult.setText("过滤数据：" + Grid1.getFilteredData());
    }

    private void bindGrid() {
        Grid1.setDataSource(FilteredTable.getFilteredRows(StudentGridData.rows(), Grid1, this::filterDataRowItem));
        Grid1.dataBind();
    }

    private boolean filterDataRowItem(Object sourceValue, GridColumnFilteredItem item, String columnId) {
        if ("Name".equals(columnId)) {
            return FilterMatchers.text(item.getOperator(), sourceValue, item.getValue());
        }
        return false;
    }
}
