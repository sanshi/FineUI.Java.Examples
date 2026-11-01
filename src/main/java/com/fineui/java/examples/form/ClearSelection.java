package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.CheckBoxList;
import com.fineui.java.core.controls.DataList;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.RadioButtonList;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.examples.code.PageBase;

import java.util.List;
import java.util.Map;

/** 七类控件使用同一个清空选择入口，并在当前事件及下一轮回发读取实际状态。 */
@FineUIPage("form/clear-selection")
public class ClearSelection extends PageBase {
    Grid Grid1;
    DropDownBox DropDownBox1;
    DropDownList DropDownList1;
    DataList DataList1;
    Tree Tree1;
    CheckBoxList CheckBoxList1;
    RadioButtonList RadioButtonList1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(List.of(
                    Map.of("Id", "a", "Name", "甲"),
                    Map.of("Id", "b", "Name", "乙（不可选）")));
            Grid1.dataBind();
        }
    }

    private void clearAllSelections() {
        Grid1.clearSelection();
        DropDownBox1.clearSelection();
        DropDownList1.clearSelection();
        DataList1.clearSelection();
        Tree1.clearSelection();
        CheckBoxList1.clearSelection();
        RadioButtonList1.clearSelection();
    }

    private void showSelection(String operation) {
        int[] counts = {
                Grid1.getSelectedRowIDArray().length,
                DropDownBox1.getValues().size(),
                DropDownList1.getSelectedValueArray().length,
                DataList1.getSelectedValueArray().length,
                Tree1.getSelectedNodeIDArray().length,
                CheckBoxList1.getSelectedValueArray().length,
                RadioButtonList1.getSelectedValue() == null ? 0 : 1
        };
        String[] cell = Grid1.getSelectedCell();
        labResult.setText(operation
                + "：表格 " + counts[0]
                + "；下拉选择框 " + counts[1]
                + "；下拉列表 " + counts[2]
                + "；数据列表 " + counts[3]
                + "；树 " + counts[4]
                + "；复选框列表 " + counts[5]
                + "；单选框列表 " + counts[6]
                + "；单元格 " + (cell == null || cell.length == 0 ? 0 : 1)
                + "；树勾选 " + Tree1.getCheckedNodeIds().size());
    }

    public void btnClearSelection_Click(Object sender, EventArgs e) {
        clearAllSelections();
        showSelection("清空后");
    }

    public void btnClearAndSelect_Click(Object sender, EventArgs e) {
        clearAllSelections();

        // 同一轮清空后仍可重新选择，清空动作不能覆盖后面的业务赋值。
        Grid1.setSelectedRowIDArray(new String[] {"a"});
        DropDownBox1.setSelection(List.of("a"), List.of("甲"));
        DropDownList1.setSelectedValue("a");
        DataList1.setSelectedValue("a");
        Tree1.setSelectedNodeID("a");
        CheckBoxList1.setSelectedValueArray(new String[] {"a"});
        RadioButtonList1.setSelectedValue("a");
        showSelection("重新选中后");
    }

    public void btnReadSelection_Click(Object sender, EventArgs e) {
        showSelection("当前");
    }

    public void btnUncheckNodes_Click(Object sender, EventArgs e) {
        Tree1.uncheckAllNodes();
        showSelection("取消勾选后");
    }
}
