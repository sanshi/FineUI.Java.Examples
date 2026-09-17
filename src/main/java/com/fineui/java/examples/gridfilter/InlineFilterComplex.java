package com.fineui.java.examples.gridfilter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumnFilteredItem;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 内联表头过滤 · 综合（路由 {@code grid-filter/inline-filter-complex}）：多列各用不同的内联过滤控件——
 * 姓名（文本 + 操作符，多条件任一满足）、性别（下拉单选）、入学年份（数字 + 操作符，多条件）、
 * 是否在校（下拉单选）、所学专业 / 分组（下拉多选 Tags）、注册日期（日期 + 操作符，多条件）。
 */
@FineUIPage("grid-filter/inline-filter-complex")
public class InlineFilterComplex extends PageBase {

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
        switch (columnId) {
            case "Name":
                return FilterMatchers.text(item.getOperator(), sourceValue, item.getValue());
            case "EntranceYear":
                return FilterMatchers.number(item.getOperator(), sourceValue, item.getValue());
            case "LogTime":
                return FilterMatchers.date(item.getOperator(), sourceValue, item.getValue());
            case "Major":
            case "Group":
                return FilterMatchers.tags(sourceValue, item.getValue());
            case "AtSchool":
                // 布尔相等（源值 true/false、过滤值 "true"/"false"）
                return String.valueOf(sourceValue).equalsIgnoreCase(String.valueOf(item.getValue()));
            case "Gender":
                // 整数相等（源值 0/1、过滤值 "0"/"1"）
                return String.valueOf(sourceValue).equals(String.valueOf(item.getValue()));
            default:
                return false;
        }
    }
}
