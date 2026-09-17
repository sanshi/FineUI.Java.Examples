package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行选择（多选，隐藏表头复选框，路由 {@code grid/check-all-hide-header}）：用 CSS 隐藏表头全选框，
 * 初始选中第 5、10 行（模板 {@code selected-row-id-array} 声明）。
 */
@FineUIPage("grid/check-all-hide-header")
public class CheckAllHideHeader extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
