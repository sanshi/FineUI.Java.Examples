package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.StudentGridData2;

/** 列样式（列自动类名 CSS 着色）（路由 {@code grid/style-column-class}）。 */
@FineUIPage("grid/style-column-class")
public class StyleColumnClass extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            autoBindGrid();
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
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
