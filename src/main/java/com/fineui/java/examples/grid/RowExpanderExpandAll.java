package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行扩展列（展开全部，路由 {@code grid/row-expander-expand-all}）：{@code expand-all-row-expanders=true} 初始展开全部，
 * 重新绑定数据后仍保持展开；配「展开全部 / 折叠全部」按钮（客户端）。
 */
@FineUIPage("grid/row-expander-expand-all")
public class RowExpanderExpandAll extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        // 重新绑定数据：expandAllRowExpanders 仍生效，所有行扩展列保持展开
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }
}
