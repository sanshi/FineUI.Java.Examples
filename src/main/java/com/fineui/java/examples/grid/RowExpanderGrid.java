package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行扩展列（嵌套表格，网址数据源，路由 {@code grid/row-expander-grid}）：
 * 展开某行时，用行数据拼出网址、以 {@code F.create} 在行扩展区内动态创建一个内嵌表格，
 * 通过 {@code dataUrl} 从数据接口拉取该学生的三次考试成绩。嵌套表格仅用于数据展示。
 */
@FineUIPage("grid/row-expander-grid")
public class RowExpanderGrid extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
