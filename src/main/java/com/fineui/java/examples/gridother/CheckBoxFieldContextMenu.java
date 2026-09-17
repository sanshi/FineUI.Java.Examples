package com.fineui.java.examples.gridother;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 复选列 + 右键菜单（路由 {@code grid-other/check-box-field-context-menu}）：右键弹出菜单全选/取消行的复选框。 */
@FineUIPage("grid-other/check-box-field-context-menu")
public class CheckBoxFieldContextMenu extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
