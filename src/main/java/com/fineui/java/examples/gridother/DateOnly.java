package com.fineui.java.examples.gridother;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.GridSelectionMessage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 仅日期 / 仅时间字段（路由 {@code grid-other/date-only}）：仅日期列输出 yyyy-MM-dd、仅时间列输出 HH:mm:ss；服务端按钮读选中行回带。 */
@FineUIPage("grid-other/date-only")
public class DateOnly extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showNotifyRaw(GridSelectionMessage.howManyRowsAreSelected(Grid1));
    }
}
