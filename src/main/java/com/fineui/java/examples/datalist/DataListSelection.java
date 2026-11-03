package com.fineui.java.examples.datalist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DataList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/** 比较单选、多选和保持当前选择，并从后台读取、赋值和清空。 */
@FineUIPage("data-list/data-list-selection")
public class DataListSelection extends PageBase {
    DataList DataListSingle;
    DataList DataListMulti;
    DataList DataListKeep;
    Label labResult;

    private void showSelection() {
        String single = DataListSingle.getSelectedValue();
        labResult.setText("单选：" + (single == null ? "" : single)
                + "；多选：" + String.join(", ", DataListMulti.getSelectedValueArray())
                + "；保持选择：" + String.join(", ", DataListKeep.getSelectedValueArray()));
    }

    public void btnRead_Click(Object sender, EventArgs e) {
        showSelection();
    }

    public void btnAssign_Click(Object sender, EventArgs e) {
        DataListSingle.setSelectedValue("guangzhou");
        DataListMulti.setSelectedValueArray(new String[] {"shanghai", "guangzhou"});
        DataListKeep.setSelectedValueArray(new String[] {"shanghai", "guangzhou"});
        showSelection();
    }

    public void btnClear_Click(Object sender, EventArgs e) {
        DataListSingle.clearSelection();
        DataListMulti.clearSelection();
        DataListKeep.clearSelection();
        showSelection();
    }
}
