package com.fineui.java.examples.gridother;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 多列表格（路由 {@code grid-other/many-columns}）：多列、多行、行高不同。 */
@FineUIPage("grid-other/many-columns")
public class ManyColumns extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 把 12 行基础数据复制 3 组（共 36 行），行 id 从 101 顺序递增
        List<Map<String, Object>> data = new ArrayList<>();
        int id = 101;
        for (int i = 0; i <= 2; i++) {
            for (Map<String, Object> row : StudentGridData.rows()) {
                row.put("Id", id++);
                data.add(row);
            }
        }

        Grid1.setDataSource(data);
        Grid1.dataBind();
    }
}
