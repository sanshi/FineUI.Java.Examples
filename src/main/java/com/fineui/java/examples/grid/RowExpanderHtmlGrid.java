package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 行扩展列（嵌套 HTML 表格，路由 {@code grid/row-expander-html-grid}）：展开行时从数据接口拉取成绩拼成 HTML 表格。 */
@FineUIPage("grid/row-expander-html-grid")
public class RowExpanderHtmlGrid extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
