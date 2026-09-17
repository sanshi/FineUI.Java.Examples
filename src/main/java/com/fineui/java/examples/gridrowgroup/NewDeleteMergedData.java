package com.fineui.java.examples.gridrowgroup;

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
 * 分组表格的新增行与删除行（MergedData）：表格启用 {@code EnableRowGroup}（按入学年份分组）与
 * {@code IncludeMergedData} 后，回发时可用 {@link Grid#getMergedData()} 拿到「所有未删除行的当前值」
 * （含新增行、已改行、未改行）。保存时直接用合并数据整表重建数据源、重新绑定，并把合并数据的
 * JSON 展示到标签。
 */
@FineUIPage("grid-row-group/new-delete-merged-data")
public class NewDeleteMergedData extends PageBase {

    private static final String SESSION_KEY = "GridRowGroup.NewDeleteMergedData";

    private static final String[] FIELDS = {"Name", "EntranceYear", "AtSchool", "Major", "Gender", "EntranceDate"};

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

    @SuppressWarnings("unchecked")
    public void btnSubmit_Click(Object sender, EventArgs e) {
        int rowIndex = 0;
        List<Map<String, Object>> newTable = new ArrayList<>();
        // 需要先启用表格的 IncludeMergedData 属性，才能在页面回发时使用 getMergedData
        for (Map<String, Object> mergedRow : Grid1.getMergedData()) {
            Object valuesObj = mergedRow.get("values");
            if (!(valuesObj instanceof Map)) {
                continue;
            }
            Map<String, Object> values = (Map<String, Object>) valuesObj;

            Map<String, Object> newRow = new LinkedHashMap<>();
            newRow.put("Id", rowIndex);   // 实际项目中请使用数据库中的自增长主键，无需设置此列的值
            for (String field : FIELDS) {
                newRow.put(field, values.get(field));
            }
            newTable.add(newRow);

            rowIndex++;
        }

        Grid1.setDataSource(newTable);
        Grid1.dataBind();

        labResult.setText("用户修改的数据：<pre>" + Json.encode(Grid1.getMergedData()) + "</pre>");

        session().setAttribute(SESSION_KEY, newTable);
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    /**
     * 服务端会话缓存的可变数据源：首次从演示数据取所需字段的精简副本放进会话，之后返回该份。
     * 特别注意：真实开发中不要在会话放大量数据，否则严重影响服务器性能。
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> copy = new ArrayList<>();
            for (Map<String, Object> row : StudentGridData.rows()) {
                Map<String, Object> simple = new LinkedHashMap<>();
                simple.put("Id", row.get("Id"));
                for (String field : FIELDS) {
                    simple.put(field, row.get(field));
                }
                copy.add(simple);
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
