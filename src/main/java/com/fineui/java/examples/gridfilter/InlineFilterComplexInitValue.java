package com.fineui.java.examples.gridfilter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumnFilteredData;
import com.fineui.java.core.controls.GridColumnFilteredItem;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.List;

/**
 * 内联表头过滤 · 综合 + 服务端初始化过滤值（路由 {@code grid-filter/inline-filter-complex-init-value}）：
 * 首屏由服务端给入学年份（大于 2008）、所学专业（化学系/物理系/数学系）、分组（分组2/分组3）设初始过滤值；
 * 「后台更新过滤数据」按钮先清空再设性别（男）+ 入学年份（大于 2008）。因首屏做了服务端过滤，需为相关列定义 column-id。
 */
@FineUIPage("grid-filter/inline-filter-complex-init-value")
public class InlineFilterComplexInitValue extends PageBase {

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            setColumnFilter("EntranceYear", new GridColumnFilteredItem("greater", 2008));
            setColumnFilter("Major", new GridColumnFilteredItem(null, List.of("化学系", "物理系", "数学系")));
            setColumnFilter("Group", new GridColumnFilteredItem(null, List.of("2", "3")));   // 分组2、分组3

            bindGrid();
            labResult.setText("初始过滤数据：" + Grid1.getFilteredData());
        }
    }

    public void Grid1_FilterChanged(Object sender, EventArgs e) {
        bindGrid();
        labResult.setText("过滤数据：" + Grid1.getFilteredData());
    }

    public void btnUpdateFilteredData_Click(Object sender, EventArgs e) {
        Grid1.setFilteredData(null);   // 先清空
        setColumnFilter("Gender", new GridColumnFilteredItem(null, 1));
        setColumnFilter("EntranceYear", new GridColumnFilteredItem("greater", 2008));
        bindGrid();
        labResult.setText("后台更新过滤数据：" + Grid1.getFilteredData());
    }

    private void setColumnFilter(String columnId, GridColumnFilteredItem item) {
        GridColumnFilteredData data = new GridColumnFilteredData();
        data.addItem(item);
        Grid1.findColumn(columnId).setColumnFilteredData(data);
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
                return String.valueOf(sourceValue).equalsIgnoreCase(String.valueOf(item.getValue()));
            case "Gender":
                return String.valueOf(sourceValue).equals(String.valueOf(item.getValue()));
            default:
                return false;
        }
    }
}
