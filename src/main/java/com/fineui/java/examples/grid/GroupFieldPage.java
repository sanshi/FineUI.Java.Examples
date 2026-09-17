package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.GroupFieldGridData;
import com.fineui.java.examples.code.PageBase;

/** 多表头（路由 {@code grid/group-field}）：省 → 市两级分组表头，叶子列为各市统计数据。 */
@FineUIPage("grid/group-field")
public class GroupFieldPage extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(GroupFieldGridData.rows());
            Grid1.dataBind();
        }
    }
}
