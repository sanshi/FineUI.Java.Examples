package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

import java.util.List;
import java.util.Map;

/**
 * 树表格单元格编辑（路由 {@code grid-tree/cell-editor}）：树形数据 + 单击单元格编辑
 * （名称/类型/大小/修改日期），保存时把客户端修改合并回数据源并重新绑定。
 */
@FineUIPage("grid-tree/cell-editor")
public class CellEditor extends PageBase {

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(FileTreeData.all());
        Grid1.dataBind();
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> source = FileTreeData.all();
        // 把客户端修改（modified）合并回数据源
        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            if ("modified".equals(String.valueOf(modifiedRow.get("status")))) {
                String rowId = String.valueOf(modifiedRow.get("id"));
                @SuppressWarnings("unchecked")
                Map<String, Object> values = (Map<String, Object>) modifiedRow.get("values");
                for (Map<String, Object> row : source) {
                    if (String.valueOf(row.get("Id")).equals(rowId)) {
                        row.putAll(values);
                        break;
                    }
                }
            }
        }
        Grid1.setDataSource(source);
        Grid1.dataBind();

        labResult.setTextRawHtml(new RawHtml(
                "用户修改的数据：<pre>" + Json.encode(Grid1.getModifiedData()) + "</pre>"));
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }
}
