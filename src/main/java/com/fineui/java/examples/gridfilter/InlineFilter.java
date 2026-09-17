package com.fineui.java.examples.gridfilter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumnFilteredItem;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 内联表头过滤 · 基础文本过滤（路由 {@code grid-filter/inline-filter}）：姓名列启用内联过滤，
 * 过滤输入框直接内嵌在表头，默认文本框 + 包含匹配；用户过滤后回发 {@code Grid1_FilterChanged}，
 * 服务端据当前过滤条件筛选数据后重绑。
 */
@FineUIPage("grid-filter/inline-filter")
public class InlineFilter extends PageBase {

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

    /** 姓名列：源值「包含」过滤值即命中。 */
    private boolean filterDataRowItem(Object sourceValue, GridColumnFilteredItem item, String columnId) {
        if ("Name".equals(columnId)) {
            String source = sourceValue == null ? "" : sourceValue.toString();
            String filtered = item.getValue() == null ? "" : item.getValue().toString();
            return source.contains(filtered);
        }
        return false;
    }
}
