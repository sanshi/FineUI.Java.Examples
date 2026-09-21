package com.fineui.java.examples.grideditor;

import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 从弹出窗体中快速选择：姓名列进入编辑后点触发按钮弹出 IFrame 窗体，选中用户后把该行数据回填到当前编辑行。
 * 保存时从客户端回带的合并数据（{@code mergedData}）重建整张表、重新绑定，并把合并数据的 JSON 展示到标签。
 */
@FineUIPage("grid-editor/select-from-window")
public class SelectFromWindow extends PageBase {

    private static final String SESSION_KEY = "GridEditor.SelectFromWindow";

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Submit_Click".equals(e.getEventName())) {
            JsonNode param = Json.parse(e.getArgument());
            submitClick(param.path("mergedData"));
        }
    }

    private void loadData() {
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    // 保存：从客户端回带的 mergedData 逐行重建整张表（Id 用序号占位，实际项目请用数据库自增主键）
    private void submitClick(JsonNode mergedData) {
        int rowIndex = 0;
        List<Map<String, Object>> newTable = new ArrayList<>();
        for (JsonNode mergedRow : mergedData) {
            JsonNode values = mergedRow.path("values");
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("Id", rowIndex);
            row.put("Name", values.path("Name").asText(""));
            row.put("EntranceYear", values.path("EntranceYear").asInt());
            row.put("AtSchool", values.path("AtSchool").asBoolean());
            row.put("Major", values.path("Major").asText(""));
            row.put("Gender", values.path("Gender").asInt());
            row.put("EntranceDate", values.path("EntranceDate").asText(""));
            newTable.add(row);
            rowIndex++;
        }

        session().setAttribute(SESSION_KEY, newTable);

        Grid1.setDataSource(newTable);
        Grid1.dataBind();

        labResult.setText("用户修改的数据：<pre>" + Json.encode(mergedData) + "</pre>");
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    /**
     * 服务端会话缓存的可变数据源：初始为空表（行由客户端「新增数据」加入、编辑后从弹出窗体选人回填）。
     * 特别注意：真实开发中不要在会话放大量数据，否则严重影响服务器性能。
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> empty = new ArrayList<>();
            session().setAttribute(SESSION_KEY, empty);
            return empty;
        }
        return (List<Map<String, Object>>) cached;
    }
}
