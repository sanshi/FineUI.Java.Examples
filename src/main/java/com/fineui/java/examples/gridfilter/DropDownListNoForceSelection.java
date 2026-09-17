package com.fineui.java.examples.gridfilter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumnFilteredItem;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 表头过滤 · 列表多选（不强制选择，路由 {@code grid-filter/drop-down-list-no-force-selection}）：所学专业列用下拉多选过滤，
 * 允许用户自由输入（{@code forceSelection=false}）。选中项时源值等于任一选中项即命中；用户自由输入时源值包含所输文本即命中。
 */
@FineUIPage("grid-filter/drop-down-list-no-force-selection")
public class DropDownListNoForceSelection extends PageBase {

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
        if ("Major".equals(columnId)) {
            // 有选中项（非空数组）→ 等于任一项；否则用户自由输入 → 包含所输文本（未选时回带空数组而非 null）
            return FilterMatchers.tagsOrInput(item.getValue(), item.getText(), sourceValue);
        }
        return false;
    }
}
