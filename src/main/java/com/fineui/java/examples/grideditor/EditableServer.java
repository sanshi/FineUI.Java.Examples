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
 * 禁止列编辑（后台更新）：初始[所学专业]列不可编辑，通过后台按钮切换指定列的可编辑状态。
 * 列是声明式结构、不随回发往返，故用会话记住每列的可编辑标志，翻转时在服务端调用 setColumnEditable
 * 让客户端即时生效。
 */
@FineUIPage("grid-editor/editable-server")
public class EditableServer extends PageBase {

    private static final String SESSION_KEY = "GridEditor.EditableServer";
    private static final String COLUMN_STATE_KEY = "GridEditor.EditableServer.columns";
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
        applyColumnEditable();
    }

    public void btnChangeEntranceDateEditable_Click(Object sender, EventArgs e) {
        toggleColumnEditable("EntranceDate");
    }

    public void btnChangeAtSchoolEditable_Click(Object sender, EventArgs e) {
        toggleColumnEditable("AtSchool");
    }

    public void btnChangeMajorEditable_Click(Object sender, EventArgs e) {
        toggleColumnEditable("Major");
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> source = sourceData();

        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            if ("modified".equals(String.valueOf(modifiedRow.get("status")))) {
                updateDataRow(modifiedRow, String.valueOf(modifiedRow.get("id")), source, COLUMNS);
            }
        }

        Grid1.setDataSource(source);
        Grid1.dataBind();
        applyColumnEditable();

        labResult.setText("用户修改的数据：<pre>" + Json.encode(Grid1.getModifiedData()) + "</pre>");

        session().setAttribute(SESSION_KEY, source);
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    private void toggleColumnEditable(String columnId) {
        Map<String, Boolean> states = columnStates();
        boolean next = !Boolean.TRUE.equals(states.get(columnId));
        states.put(columnId, next);
        Grid1.setColumnEditable(columnId, next);
        session().setAttribute(COLUMN_STATE_KEY, states);
    }

    // 按会话记住的状态重新设置每列的可编辑性（加载/重绑后调用，保持翻转结果不丢失）
    private void applyColumnEditable() {
        Map<String, Boolean> states = columnStates();
        for (Map.Entry<String, Boolean> entry : states.entrySet()) {
            Grid1.setColumnEditable(entry.getKey(), Boolean.TRUE.equals(entry.getValue()));
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Boolean> columnStates() {
        Object cached = session().getAttribute(COLUMN_STATE_KEY);
        if (cached == null) {
            Map<String, Boolean> states = new LinkedHashMap<>();
            states.put("EntranceDate", true);
            states.put("AtSchool", true);
            states.put("Major", false);
            session().setAttribute(COLUMN_STATE_KEY, states);
            return states;
        }
        return (Map<String, Boolean>) cached;
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
