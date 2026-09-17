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
 * 下拉表格（多选）作单元格编辑器：兴趣爱好列进入编辑时弹出一个可复选多选的表格（爱好代码/名称两列），
 * 勾选多行后以逗号分隔的编码存储、经 {@code renderHobby} 从弹出表格数据查名称显示。
 * 弹出表格的数据由服务端绑定。保存时把用户修改的数据落到会话缓存数据源、重新绑定，并展示修改的 JSON。
 */
@FineUIPage("grid-editor/drop-down-box-grid-multi")
public class DropDownBoxGridMulti extends PageBase {

    private static final String SESSION_KEY = "GridEditor.DropDownBoxGridMulti";
    private static final String[] COLUMNS = {"Name", "Gender", "EntranceYear", "EntranceDate", "AtSchool", "Hobby"};

    Grid Grid1;
    Grid Grid2;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();

        // 弹出表格（爱好代码/名称）由服务端绑定
        Grid2.setDataSource(hobbyTable());
        Grid2.dataBind();
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> source = sourceData();

        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            String status = String.valueOf(modifiedRow.get("status"));
            String rowId = String.valueOf(modifiedRow.get("id"));
            if ("modified".equals(status)) {
                updateDataRow(modifiedRow, rowId, source, COLUMNS);
            }
        }

        Grid1.setDataSource(source);
        Grid1.dataBind();

        labResult.setText("用户修改的数据：<pre>" + Json.encode(Grid1.getModifiedData()) + "</pre>");

        session().setAttribute(SESSION_KEY, source);
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    /** 兴趣爱好数据（代码 / 名称）。 */
    static List<Map<String, Object>> hobbyTable() {
        List<Map<String, Object>> list = new ArrayList<>();
        addHobby(list, "reading", "读书");
        addHobby(list, "basketball", "篮球");
        addHobby(list, "travel", "旅游");
        addHobby(list, "movie", "电影");
        addHobby(list, "music", "音乐");
        return list;
    }

    private static void addHobby(List<Map<String, Object>> list, String code, String name) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Code", code);
        row.put("Name", name);
        list.add(row);
    }

    /**
     * 服务端会话缓存的可变数据源：首次从演示数据深拷贝一份放进会话，之后返回该份（保存时原地修改）。
     * 特别注意：真实开发中不要在会话放大量数据，否则严重影响服务器性能。
     */
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
