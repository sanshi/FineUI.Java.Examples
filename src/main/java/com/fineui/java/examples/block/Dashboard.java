package com.fineui.java.examples.block;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** Block 响应式仪表盘。 */
@FineUIPage("block/dashboard")
public class Dashboard extends PageBase {
    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            getPageManager().set("watermark", true).set("watermarkText", "I❤︎FineUI");
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
