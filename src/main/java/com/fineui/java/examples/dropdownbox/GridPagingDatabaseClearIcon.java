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
 * 下拉表格（数据库分页，清空图标，路由 {@code drop-down-box/grid-paging-database-clear-icon}）：
 * 下拉框开启「有值时显示清空图标」（auto-show-clear-icon），点击图标在客户端清空选中值（不回发）。
 */
@FineUIPage("drop-down-box/grid-paging-database-clear-icon")
public class GridPagingDatabaseClearIcon extends PageBase {

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
}
