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
 * 内联表头过滤 · 列表多选 + 服务端初始化过滤值（路由 {@code grid-filter/inline-filter-drop-down-list-init-value}）：
 * 首屏由服务端给所学专业列设初始过滤值「化学系/物理系/数学系」；「后台更新过滤数据」按钮先清空再设「材料科学与工程系」。
 * 因首屏做了服务端过滤，需为相关列定义 column-id。
 */
@FineUIPage("grid-filter/inline-filter-drop-down-list-init-value")
public class InlineFilterDropDownListInitValue extends PageBase {

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            setColumnFilter(List.of("化学系", "物理系", "数学系"));
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
        setColumnFilter(List.of("材料科学与工程系"));
        bindGrid();
        labResult.setText("后台更新过滤数据：" + Grid1.getFilteredData());
    }

    private void setColumnFilter(List<String> values) {
        GridColumnFilteredData data = new GridColumnFilteredData();
        data.addItem(new GridColumnFilteredItem(null, values));
        Grid1.findColumn("Major").setColumnFilteredData(data);
    }

    private void bindGrid() {
        Grid1.setDataSource(FilteredTable.getFilteredRows(StudentGridData.rows(), Grid1, this::filterDataRowItem));
        Grid1.dataBind();
    }

    private boolean filterDataRowItem(Object sourceValue, GridColumnFilteredItem item, String columnId) {
        if ("Major".equals(columnId)) {
            return FilterMatchers.tags(sourceValue, item.getValue());
        }
        return false;
    }
}
