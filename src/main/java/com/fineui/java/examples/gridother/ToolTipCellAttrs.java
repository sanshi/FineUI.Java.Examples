package com.fineui.java.examples.gridother;

import java.util.List;
import java.util.Map;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 单元格属性提示（路由 {@code grid-other/tool-tip-cell-attrs}）：末行专业加粗，渲染器给单元格挂 data-qtip 提示属性。 */
@FineUIPage("grid-other/tool-tip-cell-attrs")
public class ToolTipCellAttrs extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 修改最后一行的数据
        List<Map<String, Object>> data = StudentGridData.rows();
        Map<String, Object> lastRow = data.get(data.size() - 1);
        lastRow.put("Major", "<b>" + lastRow.get("Major") + "&</b>");

        Grid1.setDataSource(data);
        Grid1.dataBind();
    }
}
