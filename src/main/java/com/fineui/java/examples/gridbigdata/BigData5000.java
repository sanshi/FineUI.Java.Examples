package com.fineui.java.examples.gridbigdata;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

/** 大数据虚拟滚动（5,000 行）。 */
@FineUIPage("grid-big-data/big-data5000")
public class BigData5000 extends PageBase {
    Grid Grid1;
    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) { Grid1.setDataSource(BigDataData.rows(5_000)); Grid1.dataBind(); }
    }
}
