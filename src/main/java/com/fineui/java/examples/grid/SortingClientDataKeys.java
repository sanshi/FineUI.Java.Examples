package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.GridSelectionMessage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 客户端排序 + 数据键（路由 {@code grid/sorting-client-data-keys}）：不定义服务端排序事件即为客户端排序；
 * 设 data-key-names 后，客户端排序不影响服务端按行 id 读取选中行。
 */
@FineUIPage("grid/sorting-client-data-keys")
public class SortingClientDataKeys extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.sortedRows(Grid1.getSortField(), Grid1.getSortDirection()));
            Grid1.dataBind();
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showNotifyRaw(GridSelectionMessage.howManyRowsAreSelected(Grid1));
    }
}
