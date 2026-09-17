package com.fineui.java.examples.gridrowheight;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 100 行（行高不同，多行文本省略）（路由 {@code grid-row-height/grid100-line-clamp}）：108 行数据（12 行 × 9 组）。 */
@FineUIPage("grid-row-height/grid100-line-clamp")
public class Grid100LineClamp extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows100());
            Grid1.dataBind();
        }
    }
}
