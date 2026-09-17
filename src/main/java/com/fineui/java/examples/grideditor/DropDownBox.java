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
 * 从下拉表格中快速选择：表格进入编辑状态后，姓名列的下拉框（DropDownBox）弹出一个只读表格，
 * 在弹出表格中单击某个用户，就把该用户的多列信息一次性回填到当前编辑行。表格初始为空，
 * 用工具栏新增行、行内命令删除，保存时把新增/修改/删除落到会话缓存数据源并重新绑定。
 *
 * <p>姓名列内部存的是用户编码（下拉表格的行 Id），经 {@code renderCode} 查询弹出表格数据转换为姓名显示；
 * 因此若弹出表格分页，这种就地转换方式将失效。
 */
@FineUIPage("grid-editor/drop-down-box")
public class DropDownBox extends PageBase {

    private static final String SESSION_KEY = "GridEditor.DropDownBox";
    private static final String[] COLUMNS = {"Code", "Name", "Gender", "EntranceYear", "EntranceDate", "AtSchool", "Major"};

    Grid Grid1;
    Grid Grid2;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 主表格初始为空（用户通过工具栏新增行）
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();

        // 弹出表格：全量用户列表（供下拉快速选择）
        Grid2.setDataSource(StudentGridData.rows());
        Grid2.dataBind();
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> source = sourceData();

        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            String status = String.valueOf(modifiedRow.get("status"));
            String rowId = String.valueOf(modifiedRow.get("id"));
            if ("modified".equals(status)) {
                updateDataRow(modifiedRow, rowId, source, COLUMNS);
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
        // 设置行ID（模拟数据库的自增长列）
        rowData.put("Id", getNextRowId(source));
        updateDataRow(modifiedRow, rowData, COLUMNS);
        return rowData;
    }

    /**
     * 服务端会话缓存的可变数据源：首次为空表（用户通过工具栏新增行），之后返回该份（保存时原地修改）。
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
