package com.fineui.java.examples.gridbigdata;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

/** 大数据虚拟滚动（1,000 行）。 */
@FineUIPage("grid-big-data/big-data1000")
public class BigData1000 extends PageBase {
    Grid Grid1;
    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) { Grid1.setDataSource(BigDataData.rows(1_000)); Grid1.dataBind(); }
    }
}
