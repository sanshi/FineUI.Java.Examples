package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行选择（保持选中行，路由 {@code grid/check-all-keep-current-selection}）：{@code keep-current-selection="true"}，
 * 点击行体累加选择（等效按住 Ctrl），不取消已选行。
 */
@FineUIPage("grid/check-all-keep-current-selection")
public class CheckAllKeepCurrentSelection extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
