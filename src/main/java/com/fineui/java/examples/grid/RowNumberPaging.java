package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 序号列（内存分页，路由 {@code grid/row-number-paging}）：序号列 {@code enable-paging-number}，翻页时序号连续。
 */
@FineUIPage("grid/row-number-paging")
public class RowNumberPaging extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 内存分页：全部数据一次性绑定，翻页由客户端完成，回发无需重绑。
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }
}
