package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.enums.SummaryPosition;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 动态修改列锁定与合计：开启普通列锁定和合计，并把合计行放到顶部。合计按当前数据计算，不需要重新绑定数据。 */
@FineUIPage("grid-dynamic/layout-summary")
public class LayoutSummary extends PageBase {
    Grid Grid1;
    Label LabelResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }

    public void Button1_Click(Object sender, EventArgs e) {
        Grid1.setAllowColumnLocking(true);
        Grid1.findColumn("Name").setLocked(true);
        Grid1.setEnableSummary(true);
        Grid1.setSummaryPosition(SummaryPosition.Top);
    }

    public void Button2_Click(Object sender, EventArgs e) {
        Grid1.findColumn("Name").setLocked(false);
        Grid1.setAllowColumnLocking(false);
        Grid1.setEnableSummary(false);
    }

    public void Button3_Click(Object sender, EventArgs e) {
        LabelResult.setText("允许锁定列：" + Grid1.isAllowColumnLocking()
                + "；姓名列锁定：" + Grid1.findColumn("Name").isLocked()
                + "；启用合计：" + Grid1.isEnableSummary()
                + "；合计位置：" + Grid1.getSummaryPosition());
    }

    public void BusinessButton_Click(Object sender, EventArgs e) {
        LabelResult.setText("业务按钮仍可回发。");
    }
}
