package com.fineui.java.examples.gridother;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.ComplexPropertyData;
import com.fineui.java.examples.code.PageBase;

/** 绑定到二级属性（路由 {@code grid-other/complex-property}）：列 data-field 支持点号路径 Info.UserName。 */
@FineUIPage("grid-other/complex-property")
public class ComplexProperty extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(ComplexPropertyData.rows());
            Grid1.dataBind();
        }
    }
}
