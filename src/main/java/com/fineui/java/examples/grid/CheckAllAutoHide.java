package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行选择（多选，多选框自动隐藏，路由 {@code grid/check-all-auto-hide}）：复选框列平时淡出、悬停/选中时显示。
 */
@FineUIPage("grid/check-all-auto-hide")
public class CheckAllAutoHide extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
