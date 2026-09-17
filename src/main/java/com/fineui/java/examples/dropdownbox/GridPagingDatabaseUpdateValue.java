package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

import java.util.Arrays;
import java.util.Collections;

/**
 * 下拉表格（数据库分页，后台更新选中值，路由 {@code drop-down-box/grid-paging-database-update-value}）：
 * 服务端更新下拉框选中值。数据库分页时选中行可能不在当前页、客户端算不出文本，故更新值时须显式给出文本字符串。
 */
@FineUIPage("drop-down-box/grid-paging-database-update-value")
public class GridPagingDatabaseUpdateValue extends PageBase {

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

    public void btnUpdateValue_Click(Object sender, EventArgs e) {
        // 数据库分页：更新值时必须手工设置文本（选中行可能不在当前页，客户端无法重算文本）
        DropDownBox1.setText("张娟娟,叶鹏");
        DropDownBox1.setValues(Arrays.asList("109", "110"));
    }

    public void btnClearValue_Click(Object sender, EventArgs e) {
        DropDownBox1.setText("");
        DropDownBox1.setValues(Collections.emptyList());
    }
}
