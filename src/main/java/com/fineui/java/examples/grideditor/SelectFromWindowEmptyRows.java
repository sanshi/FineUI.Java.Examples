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
 * 从弹出窗体中快速选择（初始空白行）：表格加载后先铺一批空白行，逐行编辑姓名列时弹出窗体选人回填。
 * 保存时只把「姓名非空」的行落库；数字/布尔类的空值以 {@code null} 保存（对应「未定义」）。
 */
@FineUIPage("grid-editor/select-from-window-empty-rows")
public class SelectFromWindowEmptyRows extends PageBase {

    private static final String SESSION_KEY = "GridEditor.SelectFromWindowEmptyRows";

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

    // 保存：只把姓名非空的行落库；数字/布尔类空值以 null 保存
    private void submitClick(JsonNode mergedData) {
        int rowIndex = 0;
        List<Map<String, Object>> newTable = new ArrayList<>();
        for (JsonNode mergedRow : mergedData) {
            JsonNode values = mergedRow.path("values");

            // 用户名不为空，才保存
            String userName = textOrEmpty(values, "Name");
            if (!userName.isEmpty()) {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put("Id", rowIndex);   // 实际项目请用数据库自增主键
                row.put("Name", userName);
                row.put("EntranceYear", nullOrInt(values, "EntranceYear"));
                row.put("AtSchool", nullOrBool(values, "AtSchool"));
                row.put("Major", textOrEmpty(values, "Major"));
                row.put("Gender", nullOrInt(values, "Gender"));
                row.put("EntranceDate", textOrEmpty(values, "EntranceDate"));
                newTable.add(row);
            }

            rowIndex++;
        }

        session().setAttribute(SESSION_KEY, newTable);

        Grid1.setDataSource(newTable);
        Grid1.dataBind();

        labResult.setText("用户修改的数据：<pre>" + Json.encode(mergedData) + "</pre>");
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    /** 空字符串（表示未定义）返回 {@code null}，否则返回整型值。 */
    private static Object nullOrInt(JsonNode values, String property) {
        String s = textOrEmpty(values, property);
        return s.isEmpty() ? null : Integer.valueOf(Integer.parseInt(s.trim()));
    }

    /** 空字符串（表示未定义）返回 {@code null}，否则返回布尔值。 */
    private static Object nullOrBool(JsonNode values, String property) {
        String s = textOrEmpty(values, property);
        return s.isEmpty() ? null : Boolean.valueOf(Boolean.parseBoolean(s.trim()));
    }

    private static String textOrEmpty(JsonNode values, String property) {
        JsonNode n = values.path(property);
        if (n.isMissingNode() || n.isNull()) {
            return "";
        }
        return n.asText("");
    }

    /**
     * 服务端会话缓存的可变数据源：初始为空表（空白行由客户端铺设，编辑后从弹出窗体选人回填）。
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
