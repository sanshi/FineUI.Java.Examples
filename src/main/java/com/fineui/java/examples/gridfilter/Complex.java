package com.fineui.java.examples.gridfilter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumnFilteredItem;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 表头过滤 · 综合（路由 {@code grid-filter/complex}）：多列各用不同过滤控件——姓名（操作符文本，多条件「任一满足」）、
 * 性别（单选）、入学年份（操作符数字，多条件）、是否在校（单选）、所学专业（复选框列表）、分组（下拉多选 Tags）、
 * 注册日期（操作符日期，多条件）。
 */
@FineUIPage("grid-filter/complex")
public class Complex extends PageBase {

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
            case "Gender":
                // 单选（RadioButtonList）：源值与选中值按相等匹配
                return FilterMatchers.text("equal", sourceValue, item.getValue());
            default:
                return false;
        }
    }
}
