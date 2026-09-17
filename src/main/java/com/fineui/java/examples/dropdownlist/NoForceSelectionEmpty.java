package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/** 下拉列表可编辑、非强制选择、初始下拉选项为空演示页（路由 {@code drop-down-list/no-force-selection-empty}）。 */
@FineUIPage("drop-down-list/no-force-selection-empty")
public class NoForceSelectionEmpty extends PageBase {

    protected com.fineui.java.core.controls.DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnRebindData_Click(Object sender, EventArgs e) {
        // PersistItems 默认 true，回发时客户端把当前列表项随 __FSTATE 带回，getItemCount() 即为
        // 点击时下拉列表的真实项数——按此在“绑定 9 项”与“清空”间切换（Items 已随回发恢复到服务端，
        // 直接读取即可，无需从客户端另取参数）。
        if (DropDownList1.getItemCount() == 0) {
            DropDownList1.setDataSource(List.of(
                    "可选项1", "可选项2", "可选项3", "可选项4", "可选项5", "可选项6",
                    "可选择项7", "可选择项8", "可选择项9"));
            DropDownList1.dataBind();
        } else {
            DropDownList1.clearItems();
        }
    }

    public void btnSetText_Click(Object sender, EventArgs e) {
        DropDownList1.setText("用户输入值");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String value = DropDownList1.getSelectedValue();
        if (value != null && !value.isEmpty()) {
            labResult.setText("选中项：" + DropDownList1.getText() + "（值：" + value + "）");
        } else {
            labResult.setText("用户输入值：" + DropDownList1.getText());
        }
    }
}
