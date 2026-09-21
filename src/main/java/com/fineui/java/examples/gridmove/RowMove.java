package com.fineui.java.examples.gridmove;

import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 行拖拽排序（路由 {@code grid-move/row-move}）：客户端「上移/下移」按钮调整选中行位置；
 * 「保存数据」把当前行顺序（行 id 数组）存进服务端会话，刷新页面时按会话里的顺序回填数据。
 */
@FineUIPage("grid-move/row-move")
public class RowMove extends PageBase {

    private static final String SESSION_KEY = "GridMove.RowMove";

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_RowMove".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());
            List<String> rowIds = new ArrayList<>();
            for (JsonNode id : p.path("rowIds")) {
                rowIds.add(id.asText());
            }
            session().setAttribute(SESSION_KEY, rowIds);
            showNotify("数据保存成功！");
        }
    }

    private void loadData() {
        Grid1.setDataSource(orderedRows());
        Grid1.dataBind();
    }

    /** 按会话里保存的行顺序重排数据；未保存则按原始顺序。 */
    private List<Map<String, Object>> orderedRows() {
        List<Map<String, Object>> rows = StudentGridData.rows();
        Object v = session().getAttribute(SESSION_KEY);
        if (!(v instanceof List<?> savedIds) || savedIds.isEmpty()) {
            return rows;
        }
        Map<String, Map<String, Object>> byId = new LinkedHashMap<>();
        for (Map<String, Object> row : rows) {
            byId.put(String.valueOf(row.get("Id")), row);
        }
        List<Map<String, Object>> ordered = new ArrayList<>();
        for (Object id : savedIds) {
            Map<String, Object> row = byId.remove(String.valueOf(id));
            if (row != null) {
                ordered.add(row);
            }
        }
        ordered.addAll(byId.values());   // 保存里没覆盖到的行追加在后（数据可能新增）
        return ordered;
    }
}
