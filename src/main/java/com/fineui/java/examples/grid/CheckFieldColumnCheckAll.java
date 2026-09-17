package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 复选框列变体（路由 {@code grid/check-field-column-check-all}）：交互全在客户端，服务端仅首屏绑定数据。 */
@FineUIPage("grid/check-field-column-check-all")
public class CheckFieldColumnCheckAll extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
