package com.fineui.java.examples.gridrowgroup;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 行分组后台切换（路由 {@code grid-row-group/row-group-ajax}）。 */
@FineUIPage("grid-row-group/row-group-ajax")
public class RowGroupAjax extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;
    com.fineui.java.core.controls.Label labGroup;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    // 切换分组时重新提供学生数据，不能用上次请求的 DataSource。
    private void bindGroup(boolean enabled, String field) {
        Grid1.setEnableRowGroup(enabled);
        Grid1.setDataRowGroupField(field);
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }

    public void btnGroupYear_Click(Object sender, EventArgs e) {
        bindGroup(true, "EntranceYear");
    }

    public void btnGroupMajor_Click(Object sender, EventArgs e) {
        bindGroup(true, "Major");
    }

    public void btnGroupOff_Click(Object sender, EventArgs e) {
        bindGroup(false, Grid1.getDataRowGroupField());
    }

    public void btnReadGroup_Click(Object sender, EventArgs e) {
        labGroup.setText("启用行分组：" + Grid1.isEnableRowGroup() + "；分组字段：" + Grid1.getDataRowGroupField());
    }
}
