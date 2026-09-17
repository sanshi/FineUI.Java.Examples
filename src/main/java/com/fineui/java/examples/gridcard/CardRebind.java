package com.fineui.java.examples.gridcard;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 卡片模式（重新绑定，路由 {@code grid-card/card-rebind}）：初始为空，按钮在「空数据」与「有数据」之间切换重绑。 */
@FineUIPage("grid-card/card-rebind")
public class CardRebind extends PageBase {

    Grid Grid1;
    HiddenField hfState;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 初始为空数据（显示空信息图）
            hfState.setText("empty");
            Grid1.setDataSource(java.util.Collections.emptyList());
            Grid1.dataBind();
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        if ("empty".equals(hfState.getText())) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
            hfState.setText("data");
        } else {
            Grid1.setDataSource(java.util.Collections.emptyList());
            Grid1.dataBind();
            hfState.setText("empty");
        }
    }
}
