package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 动态修改排序：首次关闭排序，回发后开启并按入学年份降序排列。这里使用完整内存数据；数据库排序应在排序事件中查询并绑定结果。 */
@FineUIPage("grid-dynamic/sorting")
public class Sorting extends PageBase {
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
        Grid1.setAllowSorting(true);
        Grid1.setSortingMulti(false);
        Grid1.setSortField("EntranceYear");
        Grid1.setSortDirection("DESC");
    }

    public void Button2_Click(Object sender, EventArgs e) {
        Grid1.setAllowSorting(false);
        Grid1.setSortingMulti(false);
    }

    public void Button3_Click(Object sender, EventArgs e) {
        LabelResult.setText("当前列数：" + Grid1.getColumns().size() + "；分页：" + Grid1.isAllowPaging()
                + "；页大小：" + Grid1.getPageSize() + "；排序：" + Grid1.isAllowSorting()
                + "；多选：" + Grid1.isEnableMultiSelect());
    }

    public void BusinessButton_Click(Object sender, EventArgs e) {
        LabelResult.setText("业务按钮仍可回发。");
    }

    public void Button4_Click(Object sender, EventArgs e) {
        Grid1.setAllowSorting(true);
        Grid1.setSortingMulti(true);
        Grid1.setSortFieldArray(new String[] { "EntranceYear", "DESC", "Name", "ASC" });
    }
}
