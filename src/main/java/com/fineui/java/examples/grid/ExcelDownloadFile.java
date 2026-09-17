package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 导出文件（返回物理文件，路由 {@code grid/excel-download-file}）：导出经隐藏表单 POST 到独立入口返回物理文件内容下载。 */
@FineUIPage("grid/excel-download-file")
public class ExcelDownloadFile extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
