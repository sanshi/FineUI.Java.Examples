package com.fineui.java.examples.gridother;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 表格为空的提示（内置图标和文案）（路由 {@code grid-other/empty-icon-text}）：无数据时显示空信息；「重新绑定表格」按钮切换有/无数据。 */
@FineUIPage("grid-other/empty-icon-text")
public class EmptyIconText extends PageBase {

    Grid Grid1;
    HiddenField hfState;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            hfState.setText("empty");
            if ("data".equals("empty")) {
                Grid1.setDataSource(StudentGridData.rows());
                Grid1.dataBind();
            }
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        if ("data".equals(hfState.getText())) {
            Grid1.setDataSource(null);
            Grid1.dataBind();
            hfState.setText("empty");
        } else {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
            hfState.setText("data");
        }
    }
}
