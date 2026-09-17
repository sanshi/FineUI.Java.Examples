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
 * 下拉表格（多选，分页，自定义编辑器设置函数，服务端数据）：兴趣爱好列进入编辑时弹出可复选、带分页的多选表格。
 * 与「自定义编辑器设置函数」页不同，这里的显示文本由服务端预先算好、存进隐藏列 {@code HobbyText}，
 * {@code renderHobby} 直接读该隐藏列即可；配自定义编辑器获取函数 {@code editGetterHobby}（结束编辑时把编辑器文本回写隐藏列）
 * 与设置函数 {@code editSetterHobby}（进入编辑时用隐藏列文本作显示）。
 * 保存时把用户修改的数据（含隐藏列文本）落到会话缓存数据源、重新绑定，并展示修改的 JSON。
 */
@FineUIPage("grid-editor/drop-down-box-grid-multi-paging-server-hobby")
public class DropDownBoxGridMultiPagingServerHobby extends PageBase {

    private static final String SESSION_KEY = "GridEditor.DropDownBoxGridMultiPagingServerHobby";
    private static final String[] COLUMNS = {"Name", "Gender", "EntranceYear", "EntranceDate", "AtSchool", "Hobby", "HobbyText"};

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

        // 弹出表格（爱好代码/名称）由服务端绑定（客户端分页，全量下发）
        Grid2.setDataSource(DropDownBoxGridMulti.hobbyTable());
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

    /** 按逗号分隔的爱好编码算出中文显示文本（逗号拼接）。 */
    private static String hobbyText(String hobbyCodes) {
        if (hobbyCodes == null || hobbyCodes.isEmpty()) {
            return "";
        }
        List<String> codes = List.of(hobbyCodes.split(","));
        List<String> names = new ArrayList<>();
        for (Map<String, Object> hobby : DropDownBoxGridMulti.hobbyTable()) {
            if (codes.contains(String.valueOf(hobby.get("Code")))) {
                names.add(String.valueOf(hobby.get("Name")));
            }
        }
        return String.join(", ", names);
    }

    /**
     * 服务端会话缓存的可变数据源：首次从演示数据深拷贝一份、并给每行补上服务端预算的 {@code HobbyText} 显示文本，
     * 放进会话，之后返回该份（保存时原地修改）。
     * 特别注意：真实开发中不要在会话放大量数据，否则严重影响服务器性能。
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> copy = new ArrayList<>();
            for (Map<String, Object> row : StudentGridData.rows()) {
                Map<String, Object> r = new LinkedHashMap<>(row);
                // 修改数据源的结构，增加 HobbyText 项（服务端预算爱好显示文本）
                r.put("HobbyText", hobbyText(String.valueOf(r.get("Hobby"))));
                copy.add(r);
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
