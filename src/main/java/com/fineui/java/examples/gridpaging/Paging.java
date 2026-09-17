package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 内存分页（路由 {@code grid-paging/paging}）：数据一次性全绑，翻页在客户端完成（无服务端回发）。
 */
@FineUIPage("grid-paging/paging")
public class Paging extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 内存分页：全部数据一次性绑定，翻页/选中都由客户端保管，回发无需重绑。
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }
}
