package com.fineui.java.examples.gridother;

import java.util.List;
import java.util.Map;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 单元格提示显示（路由 {@code grid-other/tool-tip-show-tool-tip}）：末行专业加粗，列开启单元格提示。 */
@FineUIPage("grid-other/tool-tip-show-tool-tip")
public class ToolTipShowTooltip extends PageBase {

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
        lastRow.put("Major", "<b>\"" + lastRow.get("Major") + "&</b>");

        Grid1.setDataSource(data);
        Grid1.dataBind();
    }
}
