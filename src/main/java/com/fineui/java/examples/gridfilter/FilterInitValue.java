package com.fineui.java.examples.gridfilter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumnFilteredData;
import com.fineui.java.core.controls.GridColumnFilteredItem;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 表头过滤 · 服务端初始化过滤值（路由 {@code grid-filter/filter-init-value}）：首屏由服务端给姓名列设初始过滤值「张」；
 * 「后台更新过滤数据」按钮先清空再设「婷婷」。
 */
@FineUIPage("grid-filter/filter-init-value")
public class FilterInitValue extends PageBase {

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            setColumnFilter("张");
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
        setColumnFilter("婷婷");
        bindGrid();
        labResult.setText("后台更新过滤数据：" + Grid1.getFilteredData());
    }

    private void setColumnFilter(String value) {
        GridColumnFilteredData data = new GridColumnFilteredData();
        data.addItem(new GridColumnFilteredItem(null, value));
        Grid1.findColumn("Name").setColumnFilteredData(data);
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
