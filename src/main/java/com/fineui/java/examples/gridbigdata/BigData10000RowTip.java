package com.fineui.java.examples.gridbigdata;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

/** 大数据虚拟滚动（10,000 行，自定义可见行范围提示）。 */
@FineUIPage("grid-big-data/big-data10000-row-tip")
public class BigData10000RowTip extends PageBase {
    Grid Grid1;
    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) { Grid1.setDataSource(BigDataData.rows(10_000)); Grid1.dataBind(); }
    }
}
