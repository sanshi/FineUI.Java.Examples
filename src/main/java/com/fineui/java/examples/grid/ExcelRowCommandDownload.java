package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 导出文件（行内下载，自定义 POST 请求）（路由 {@code grid/excel-row-command-download}）：导出经隐藏表单 POST 到独立入口生成下载文件。 */
@FineUIPage("grid/excel-row-command-download")
public class ExcelRowCommandDownload extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
