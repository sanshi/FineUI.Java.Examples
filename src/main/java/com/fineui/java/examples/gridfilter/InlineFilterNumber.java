package com.fineui.java.examples.gridfilter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumnFilteredItem;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 内联表头过滤 · 数字 / 日期范围（路由 {@code grid-filter/inline-filter-number}）：入学年份列用数字框 + 操作符
 * （大于/小于/等于）；注册日期列用日期框 + 操作符。过滤输入框内嵌在表头，均启用多条件。
 */
@FineUIPage("grid-filter/inline-filter-number")
public class InlineFilterNumber extends PageBase {

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
        if ("EntranceYear".equals(columnId)) {
            return FilterMatchers.number(item.getOperator(), sourceValue, item.getValue());
        }
        if ("LogTime".equals(columnId)) {
            return FilterMatchers.date(item.getOperator(), sourceValue, item.getValue());
        }
        return false;
    }
}
