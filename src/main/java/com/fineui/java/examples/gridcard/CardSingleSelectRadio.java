package com.fineui.java.examples.gridcard;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.GridSelectionMessage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 卡片选择（单选，单选框样式）（路由 {@code grid-card/card-single-select-radio}）。 */
@FineUIPage("grid-card/card-single-select-radio")
public class CardSingleSelectRadio extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
            Grid1.setSelectedRowIdArray(new String[] {"106"});
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showNotifyRaw(GridSelectionMessage.howManyRowsAreSelected(Grid1));
    }

    public void Button2_Click(Object sender, EventArgs e) {
        Grid1.setSelectedRowIdArray(new String[] {"106"});   // 服务端选中第 6 行
    }
}
