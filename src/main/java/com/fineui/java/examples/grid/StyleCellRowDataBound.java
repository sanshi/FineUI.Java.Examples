package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 客户端行绑定给单元格着色（路由 {@code grid/style-cell-row-data-bound}）：客户端行绑定函数按数据条件给单元格加
 * CSS 类（{@code values['字段.cls']}）。点击按钮重新绑定数据、着色随之重新应用。
 */
@FineUIPage("grid/style-cell-row-data-bound")
public class StyleCellRowDataBound extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            autoBindGrid();
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        // 重新绑定数据（客户端行绑定着色函数会在数据加载时重新应用）
        autoBindGrid();
    }

    /** 交替绑定两张学生表：用控件自定义属性 data-source-key 跨请求记录当前表。 */
    private void autoBindGrid() {
        String sourceKey = dataSourceKey();
        if (sourceKey == null || sourceKey.isEmpty() || "table2".equals(sourceKey)) {
            Grid1.setDataSource(StudentGridData.rows());
            sourceKey = "table1";
        } else {
            Grid1.setDataSource(StudentGridData2.rows());
            sourceKey = "table2";
        }
        Grid1.dataBind();
        Grid1.setAttribute("data-source-key", sourceKey);
    }

    private String dataSourceKey() {
        String v = Grid1.getAttribute("data-source-key");
        return v == null ? "" : v;
    }
}
