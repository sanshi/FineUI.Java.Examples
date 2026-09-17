package com.fineui.java.examples.grideditor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 「从弹出窗体中快速选择」的 IFrame 子页：一个只读用户列表，双击或点「选择后关闭」把选中行数据回填到父页当前编辑行。
 */
@FineUIPage("grid-editor/select-from-window-iframe-window")
public class SelectFromWindowIFrameWindow extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
