package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行选择（只能通过全选框选中行，路由 {@code grid/check-all-only}）：{@code check-box-select-only="true"}，
 * 点击行体不选中，只能点复选框列选中。
 */
@FineUIPage("grid/check-all-only")
public class CheckAllOnly extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
