package com.fineui.java.examples.grideditor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据改变（启用禁用保存按钮）：[保存数据]按钮默认禁用，当有数据变化时（删除行、新增行、修改单元格）客户端启用它。
 * 保存时按新增 / 修改 / 删除把用户修改的数据落到服务端会话缓存并重新绑定。
 */
@FineUIPage("grid-editor/data-change")
public class DataChange extends PageBase {

    private static final String SESSION_KEY = "GridEditor.DataChange";
    private static final String[] COLUMNS = {"Name", "Gender", "EntranceYear", "EntranceDate", "AtSchool", "Major"};

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    public void btnRejectChanges_Click(Object sender, EventArgs e) {
        // 只撤销浏览器里未保存的编辑，不重绑，也不修改会话数据源。
        Grid1.rejectChanges();
    }

    public void btnReadChanges_Click(Object sender, EventArgs e) {
        // 再次回发读取，验证浏览器撤销后不再提交旧修改。
        labResult.setText("未保存的修改记录数：" + Grid1.getModifiedData().size());
    }

    public void btnCommitChanges_Click(Object sender, EventArgs e) {
        // 只接受当前客户端编辑结果；故意不写入会话，以便刷新核对数据源仍未变化。
        Grid1.commitChanges();
    }

    public void btnUpdateCells_Click(Object sender, EventArgs e) {
        // 示例数据的稳定 ID 为 101；两次改值按入队顺序执行，仍只形成一条行修改记录。
        Grid1.updateCellValue("101", "Name", "服务端修改");
        Grid1.updateCellValue("101", Map.of("Gender", 0, "Major", "服务端专业"));
    }

    public void btnUpdateAndCommit_Click(Object sender, EventArgs e) {
        // 同一次响应先改值再确认编辑基线；确认不会把结果写入服务端数据源。
        Grid1.updateCellValue("101", "Name", "服务端修改后确认");
        Grid1.commitChanges();
    }

    public void btnAddRecord_Click(Object sender, EventArgs e) {
        // 显式 ID 每次生成，避免重复点击产生重号；新增只进入客户端编辑状态。
        Map<String, Object> record = Map.of(
                "id", "server-" + java.util.UUID.randomUUID(),
                "values", Map.of("Name", "服务端新增", "Gender", 1, "Major", "新专业"));
        Grid1.addNewRecord(record, 1, "Name");
    }

    public void btnAppendRecord_Click(Object sender, EventArgs e) {
        Grid1.addNewRecord(Map.of("Name", "末尾新增", "Gender", 0, "Major", "新专业"), true);
    }

    public void btnDeleteRow_Click(Object sender, EventArgs e) {
        Grid1.deleteRow("101");
    }

    public void btnForceDeleteRow_Click(Object sender, EventArgs e) {
        // 强制删除仅移除客户端行，撤销不能恢复；刷新仍可从原数据源加载。
        Grid1.deleteRow("102", true);
    }

    public void btnDeleteSelected_Click(Object sender, EventArgs e) {
        Grid1.deleteSelectedRows();
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> source = sourceData();

        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            String status = String.valueOf(modifiedRow.get("status"));
            String rowId = String.valueOf(modifiedRow.get("id"));
            if ("modified".equals(status)) {
                fillRow(modifiedRow, findRowById(source, rowId));
            } else if ("deleted".equals(status)) {
                deleteRowById(source, rowId);
            }
        }

        // 新增行：客户端把它放在第几行，回发数据的 index 就是几，服务端照着插
        // （前提是表格不分页、也没在客户端排过序，否则 index 与数据源的行序对不上）
        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            if ("newadded".equals(String.valueOf(modifiedRow.get("status")))) {
                source.add(((Number) modifiedRow.get("index")).intValue(), createNewData(modifiedRow, source));
            }
        }

        Grid1.setDataSource(source);
        Grid1.dataBind();

        labResult.setText("用户修改的数据：<pre>" + Json.encode(Grid1.getModifiedData()) + "</pre>");

        session().setAttribute(SESSION_KEY, source);
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    private Map<String, Object> createNewData(Map<String, Object> modifiedRow, List<Map<String, Object>> source) {
        Map<String, Object> rowData = new LinkedHashMap<>();
        // 设置行 ID（模拟数据库的自增长列）
        rowData.put("Id", getNextRowId(source));
        fillRow(modifiedRow, rowData);
        return rowData;
    }

    @SuppressWarnings("unchecked")
    private void fillRow(Map<String, Object> modifiedRow, Map<String, Object> rowData) {
        if (rowData == null) {
            return;
        }
        Object valuesObj = modifiedRow.get("values");
        if (!(valuesObj instanceof Map)) {
            return;
        }
        Map<String, Object> values = (Map<String, Object>) valuesObj;
        for (String column : COLUMNS) {
            if (values.containsKey(column)) {
                rowData.put(column, values.get(column));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> copy = new ArrayList<>();
            for (Map<String, Object> row : StudentGridData.rows()) {
                copy.add(new LinkedHashMap<>(row));
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
