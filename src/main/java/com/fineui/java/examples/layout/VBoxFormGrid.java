package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** VBox 中组合长表单与撑满剩余空间的表格。 */
@FineUIPage("layout/vbox-form-grid")
public class VBoxFormGrid extends PageBase {

    Grid Grid2;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid2.setDataSource(StudentGridData.rows());
            Grid2.dataBind();
        }
    }
}
