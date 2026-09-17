package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.GroupFieldComplexGridData;
import com.fineui.java.examples.code.PageBase;

/** 复杂多表头（路由 {@code grid/group-field-complex}）：三级嵌套、分组内叶子列与子分组同级混合。 */
@FineUIPage("grid/group-field-complex")
public class GroupFieldComplex extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(GroupFieldComplexGridData.rows());
            Grid1.dataBind();
        }
    }
}
