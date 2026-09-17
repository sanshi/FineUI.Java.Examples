package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.GridSelectionMessage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行选择（多选，路由 {@code grid/check-all}）：复选框选择列，初始选中第 5、10 行；
 * 客户端 / 服务端程序化选中；服务端读取当前选中行及其数据键。
 */
@FineUIPage("grid/check-all")
public class CheckAll extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
        Grid1.setSelectedRowIdArray(new String[] {"105", "110"});   // 初始选中第 5、10 行
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showNotifyRaw(GridSelectionMessage.howManyRowsAreSelected(Grid1));
    }

    public void Button2_Click(Object sender, EventArgs e) {
        Grid1.setSelectedRowIdArray(new String[] {"102", "106", "108"});   // 服务端选中第 2、6、8 行
    }
}
