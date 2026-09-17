package com.fineui.java.examples.gridother;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 在新标签页中打开（关闭后刷新父选项卡）（路由 {@code grid-other/new-tab-hide-refresh}）：行内「编辑」命令与工具栏「新增/删除」用于在多标签页宿主中打开编辑页。 */
@FineUIPage("grid-other/new-tab-hide-refresh")
public class NewTabHideRefresh extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
