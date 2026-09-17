package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

import java.util.List;

/**
 * 多选标签（下拉表格，数据库分页，后台更新选中值）演示页（路由 {@code drop-down-box/tags-grid-paging-database-update-value}）：
 * 弹出面板内是开启数据库分页的多选表格，切换分页在服务端回发 {@link #Grid1_PageIndexChanged} 只取当前页数据。
 * 因分页表格客户端取不到跨页文本，后台更新选中值时必须手工设置文本；「清空下拉框的值」同时清文本与值。
 */
@FineUIPage("drop-down-box/tags-grid-paging-database-update-value")
public class TagsGridPagingDatabaseUpdateValue extends PageBase {

    DropDownBox DropDownBox1;
    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 1. 设置总项数（数据库分页初始化时一定要设置总记录数）
        Grid1.setRecordCount(StudentGridData2.count());
        // 2. 获取当前分页数据
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
        // 分页表格必须手工设置文本，否则客户端无法取到跨页值对应的文本
        DropDownBox1.setText("张娟娟,叶鹏");
        DropDownBox1.setValues(List.of("109", "110"));
    }

    public void btnClearValue_Click(Object sender, EventArgs e) {
        DropDownBox1.setText("");
        DropDownBox1.setValues(List.of());
    }
}
