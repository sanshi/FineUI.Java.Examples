package com.fineui.java.examples.grideditor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.RenderField;
import com.fineui.java.examples.code.DataSourceUtil;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 单元格编辑（下拉列表，省市联动）：所在省下拉列表提供全部省份；进入所在市编辑前根据当前省动态加载市列表，
 * 未选省时禁用市下拉；更改省后若原市不属于新省则清空市。省市联动所需的「省→市」数据经 {@link #getShiJson()}
 * 输出到页面脚本供客户端使用。
 */
@FineUIPage("grid-editor/sheng-shi")
public class ShengShi extends PageBase {

    private static final String SESSION_KEY = "GridEditor.ShengShi";
    private static final String[] COLUMNS = {"Name", "Gender", "Sheng", "Shi"};

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 所在省下拉列表：全部省份（名称即值）。编辑器控件不进注册表、不能字段绑定，故经所在列取编辑器后设数据。
        DropDownList ddlSheng = (DropDownList) ((RenderField) Grid1.findColumn("Sheng")).getEditor();
        ddlSheng.setDataSource(DataSourceUtil.SHENG);
        ddlSheng.dataBind();

        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    /** 省→市数据（名称）的 JSON，供模板输出到 window._SHI 供客户端联动使用。 */
    public String getShiJson() {
        return Json.encode(DataSourceUtil.SHI);
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
            for (Map<String, Object> row : ShengShiGridData.rows()) {
                copy.add(new LinkedHashMap<>(row));
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
