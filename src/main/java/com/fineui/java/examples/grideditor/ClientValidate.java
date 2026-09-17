package com.fineui.java.examples.grideditor;

import com.fineui.java.core.CustomEventArgs;
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
 * 单元格编辑（结束编辑，姓名不能为空）：保存前由客户端脚本遍历用户修改的数据校验「姓名」非空，
 * 校验不通过则弹框并定位到该单元格；通过后经自定义事件回发保存（支持修改 / 新增 / 删除三种改动）。
 */
@FineUIPage("grid-editor/client-validate")
public class ClientValidate extends PageBase {

    private static final String SESSION_KEY = "GridEditor.ClientValidate";
    private static final String[] COLUMNS = {"Name", "Gender", "EntranceYear", "EntranceDate", "AtSchool", "Major"};

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    // 对应客户端 F.customEvent('Submit_Click')：客户端校验通过后才触发
    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Submit_Click".equals(e.getEventName())) {
            submitClick();
        }
    }

    private void loadData() {
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    private void submitClick() {
        List<Map<String, Object>> source = sourceData();

        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            String status = String.valueOf(modifiedRow.get("status"));
            String rowId = String.valueOf(modifiedRow.get("id"));

            if ("modified".equals(status)) {
                updateDataRow(modifiedRow, findRowById(source, rowId), COLUMNS);
            } else if ("deleted".equals(status)) {
                deleteRowById(source, rowId);
            }
        }

        // 新增行：客户端把它放在第几行，回发数据的 index 就是几，服务端照着插
        // （前提是表格不分页、也没在客户端排过序，否则 index 与数据源的行序对不上）
        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            if ("newadded".equals(String.valueOf(modifiedRow.get("status")))) {
                // 设置行 Id（模拟数据库的自增长列）
                Map<String, Object> rowData = new LinkedHashMap<>();
                rowData.put("Id", getNextRowId(source));
                updateDataRow(modifiedRow, rowData, COLUMNS);
                source.add(((Number) modifiedRow.get("index")).intValue(), rowData);
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
