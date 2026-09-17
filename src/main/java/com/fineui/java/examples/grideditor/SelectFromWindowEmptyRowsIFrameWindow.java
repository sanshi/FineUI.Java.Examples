package com.fineui.java.examples.grideditor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 「从弹出窗体中快速选择（初始空白行）」的 IFrame 子页：只读用户列表，选中后可「定位到下一行」继续录入或「选择后关闭」。
 */
@FineUIPage("grid-editor/select-from-window-empty-rows-iframe-window")
public class SelectFromWindowEmptyRowsIFrameWindow extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
