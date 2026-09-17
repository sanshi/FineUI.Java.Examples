package com.fineui.java.examples.grid;

import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.CheckBoxList;
import com.fineui.java.examples.code.PageBase;

/**
 * 选择导出列的 IFrame 页（路由 {@code grid/excel-select-columns-iframe}）：三列复选框列表（默认全选），
 * 点「导出」时关闭本窗体并回调父页 {@code exportToExcel} 函数、传入所选列。
 */
@FineUIPage("grid/excel-select-columns-iframe")
public class ExcelSelectColumnsIFrame extends PageBase {

    CheckBoxList cblColumns;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 关闭本窗体，然后执行父页的 exportToExcel 函数并传入所选列。 */
    public void btnSaveContinue_Click(Object sender, EventArgs e) {
        ActiveWindow.hideCallParentFunction("exportToExcel", (Object) cblColumns.getSelectedValueArray());
    }
}
