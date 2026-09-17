package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行选择（禁止选择行，默认选中，路由 {@code grid/check-all-row-selectable-selected}）：不可选行禁用样式，
 * 初始按行 id 选中第 103、105 行（模板 {@code selected-row-id-array} 声明）。
 */
@FineUIPage("grid/check-all-row-selectable-selected")
public class CheckAllRowSelectableSelected extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
