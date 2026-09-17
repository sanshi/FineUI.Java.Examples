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
 * 下拉表格（多选，分页，自定义编辑器设置函数）：兴趣爱好列进入编辑时弹出一个可复选、带分页的多选表格。
 * 因表格分页后需要的行可能在其他分页，故显示文本不再从表格数据现取，而是从页面内全量爱好表 {@code window.hobbyTable} 查得；
 * 并配自定义编辑器设置函数 {@code editSetterHobby}（进入编辑时用全量爱好表把编码转成显示文本）。
 * 保存时把用户修改的数据落到会话缓存数据源、重新绑定，并展示修改的 JSON。
 */
@FineUIPage("grid-editor/drop-down-box-grid-multi-paging")
public class DropDownBoxGridMultiPaging extends PageBase {

    private static final String SESSION_KEY = "GridEditor.DropDownBoxGridMultiPaging";
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
