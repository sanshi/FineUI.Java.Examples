package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 导出文件（选择需要导出的列，路由 {@code grid/excel-select-columns}）：弹出一个 IFrame 窗体选择要导出的列，
 * 窗体内确认后回调父页 {@code exportToExcel} 函数、把所选列 POST 到独立入口，只导出被选中的列。
 */
@FineUIPage("grid/excel-select-columns")
public class ExcelSelectColumns extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
