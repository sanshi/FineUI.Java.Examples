package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** Anchor 布局演示：百分比与偏移量锚定，以及等价的 VBox 实现。 */
@FineUIPage("layout/anchor")
public class Anchor extends PageBase {

    Grid Grid1;
    Grid Grid2;
    Grid Grid3;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
            Grid2.setDataSource(StudentGridData.rows());
            Grid2.dataBind();
            Grid3.setDataSource(StudentGridData.rows());
            Grid3.dataBind();
        }
    }
}
