package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行选择（禁止选择行，整行变灰，路由 {@code grid/check-all-row-selectable-disabled}）：不可选行显示为禁用样式。
 */
@FineUIPage("grid/check-all-row-selectable-disabled")
public class CheckAllRowSelectableDisabled extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
