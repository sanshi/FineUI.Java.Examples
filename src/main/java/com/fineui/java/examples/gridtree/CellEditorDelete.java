package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 树表格单元格编辑 + 删除（路由 {@code grid-tree/cell-editor-delete}）：在
 * {@code cell-editor} 基础上增加行命令删除与「删除选中行」按钮；保存时把客户端的
 * 修改（modified）与删除（deleted）合并回数据源并重新绑定。
 */
@FineUIPage("grid-tree/cell-editor-delete")
public class CellEditorDelete extends PageBase {

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
        // 把客户端修改（modified）合并、删除（deleted）移除
        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            String status = String.valueOf(modifiedRow.get("status"));
            String rowId = String.valueOf(modifiedRow.get("id"));
            if ("modified".equals(status)) {
                @SuppressWarnings("unchecked")
                Map<String, Object> values = (Map<String, Object>) modifiedRow.get("values");
                for (Map<String, Object> row : source) {
                    if (String.valueOf(row.get("Id")).equals(rowId)) {
                        row.putAll(values);
                        break;
                    }
                }
            } else if ("deleted".equals(status)) {
                deleteRowTree(source, rowId);
            }
        }
        Grid1.setDataSource(source);
        Grid1.dataBind();

        labResult.setTextRawHtml(new RawHtml(
                "用户修改的数据：<pre>" + Json.encode(Grid1.getModifiedData()) + "</pre>"));
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    /** 递归删除某行及其所有子孙（行命令/删除选中行删的是整个子树）。 */
    private void deleteRowTree(List<Map<String, Object>> source, String rowId) {
        // 先收集直接子节点 id（避免遍历时递归修改列表）
        List<String> children = new ArrayList<>();
        for (Map<String, Object> row : source) {
            if (String.valueOf(row.get("ParentId")).equals(rowId)) {
                children.add(String.valueOf(row.get("Id")));
            }
        }
        for (String childId : children) {
            deleteRowTree(source, childId);
        }
        source.removeIf(row -> String.valueOf(row.get("Id")).equals(rowId));
    }
}
