package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 下拉表格（内存分页，路由 {@code drop-down-box/grid-paging}）：弹出面板内放一个开启内存分页的多选表格，
 * 一次性绑定全部数据、客户端翻页，跨页选中由表格「保持当前选择」维护；「获取下拉框的选中值」回发读取下拉框文本与值。
 */
@FineUIPage("drop-down-box/grid-paging")
public class GridPaging extends PageBase {

    DropDownBox DropDownBox1;
    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 内存分页：首次加载一次性绑定全部数据，翻页在客户端完成
            Grid1.setDataSource(StudentGridData2.rows());
            Grid1.dataBind();
        }
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
