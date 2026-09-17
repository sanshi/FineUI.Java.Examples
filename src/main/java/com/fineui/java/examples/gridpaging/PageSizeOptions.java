package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 页大小选择器（内存分页，路由 {@code grid-paging/page-size-options}）：只需 {@code show-page-size-selector="true"}
 * 即在分页栏内置「每页记录数」下拉，内存分页下选择后客户端即时改变行数。
 */
@FineUIPage("grid-paging/page-size-options")
public class PageSizeOptions extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
    }
}
