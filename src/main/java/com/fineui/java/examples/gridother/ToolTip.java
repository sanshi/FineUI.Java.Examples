package com.fineui.java.examples.gridother;

import java.util.List;
import java.util.Map;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 单元格提示（路由 {@code grid-other/tool-tip}）。 */
@FineUIPage("grid-other/tool-tip")
public class ToolTip extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 修改最后一行的数据，演示提示内容的 HTML 转义
        List<Map<String, Object>> rows = StudentGridData.rows();
        Map<String, Object> lastRow = rows.get(rows.size() - 1);
        lastRow.put("Major", "<b>" + lastRow.get("Major") + "&</b>");

        Grid1.setDataSource(rows);
        Grid1.dataBind();
    }
}
