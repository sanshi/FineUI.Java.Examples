package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.enums.PagingType;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 动态修改分页（内存数据）：首次加载完整数据，随后切换分页及页大小不再查询数据。分页器中的业务按钮始终保留。 */
@FineUIPage("grid-dynamic/paging-memory")
public class PagingMemory extends PageBase {
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
        Grid1.setAllowPaging(true);
        Grid1.setPageIndex(0);
        Grid1.setPageSize(5);
        Grid1.setShowPageSizeSelector(true);
        Grid1.setPageSizeOptions(3, 5, 10);
    }

    public void Button2_Click(Object sender, EventArgs e) {
        Grid1.setAllowPaging(false);
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
        Grid1.setPagingType(PagingType.NumberButton);
    }
}
