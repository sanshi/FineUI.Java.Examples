package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 下拉表格（数据库分页，清空图标回发事件，路由 {@code drop-down-box/grid-paging-database-clear-icon-click}）：
 * 在「清空图标」基础上再绑定清空图标点击事件（on-clear-icon-click），点击图标清值并回发触发服务端处理。
 */
@FineUIPage("drop-down-box/grid-paging-database-clear-icon-click")
public class GridPagingDatabaseClearIconClick extends PageBase {

    DropDownBox DropDownBox1;
    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 1.设置总项数（数据库分页初始化时一定要设置总记录数）
        Grid1.setRecordCount(StudentGridData2.count());
        // 2.获取当前分页数据
        Grid1.setDataSource(StudentGridData2.paged(Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownBox1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", text, String.join(", ", DropDownBox1.getValues())));
        } else {
            labResult.setText("下拉框为空");
        }
    }

    public void DropDownBox1_ClearIconClick(Object sender, EventArgs e) {
        labResult.setText("你点击了清空图标");
    }
}
