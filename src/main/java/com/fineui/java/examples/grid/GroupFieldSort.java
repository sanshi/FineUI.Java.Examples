package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import com.fineui.java.examples.code.GroupFieldGridData;
import com.fineui.java.examples.code.PageBase;

/** 多表头 + 排序（路由 {@code grid/group-field-sort}）：叶子列可排序，初始按驻马店数据一（HZData1）升序。 */
@FineUIPage("grid/group-field-sort")
public class GroupFieldSort extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setSortField("HZData1");
            Grid1.setSortDirection("ASC");

            List<Map<String, Object>> rows = new ArrayList<>(GroupFieldGridData.rows());
            rows.sort(Comparator.comparingInt(r -> ((Number) r.get("HZData1")).intValue()));
            Grid1.setDataSource(rows);
            Grid1.dataBind();
        }
    }
}
