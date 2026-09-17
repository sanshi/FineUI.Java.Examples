package com.fineui.java.examples.gridmerge;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 单元格合并（路由 {@code grid-merge/cells}）：合并逻辑全部在客户端 dataload 监听里完成。 */
@FineUIPage("grid-merge/cells")
public class Cells extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
