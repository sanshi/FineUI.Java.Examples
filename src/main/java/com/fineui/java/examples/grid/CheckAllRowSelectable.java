package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行选择（禁止选择行，路由 {@code grid/check-all-row-selectable}）：客户端行数据绑定回调
 * {@code onGrid1RowDataBound} 把入学年份 &gt;= 2008 的行标记为不可选。
 */
@FineUIPage("grid/check-all-row-selectable")
public class CheckAllRowSelectable extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
