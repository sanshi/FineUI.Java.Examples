package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 导出文件（多表头，路由 {@code grid/excel-group-field}）：把客户端解析出的多级表头结构 POST 到独立入口，重建含 rowspan/colspan 的多表头 Excel。 */
@FineUIPage("grid/excel-group-field")
public class ExcelGroupField extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
