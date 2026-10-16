package com.fineui.java.examples.grideditor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.RenderField;
import com.fineui.java.examples.code.ProvinceCityCountyData;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 单元格编辑（下拉列表，省市联动，客户端获取省市名称）：表格保存固定编码，
 * 显示时由客户端渲染函数查名称并附上编码，例如“河南(002)”。省市关系通过非执行 JSON 数据块传给客户端。
 */
@FineUIPage("grid-editor/sheng-shi-text-value")
public class ShengShiTextValue extends PageBase {

    private static final String SESSION_KEY = "GridEditor.ShengShiTextValue";
    private static final String[] COLUMNS = {"Name", "Gender", "Sheng", "Shi"};

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 所在省下拉列表：全部省份（首屏；客户端 F.ready 会以带代码的省数据重新加载）。
        // 编辑器控件不进注册表、不能字段绑定，故经所在列取编辑器后设数据。
        DropDownList ddlSheng = (DropDownList) ((RenderField) Grid1.findColumn("Sheng")).getEditor();
        ProvinceCityCountyData.bind(ddlSheng, ProvinceCityCountyData.provinces());

        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    /** 把固定省市编码和名称作为非执行 JSON 交给页面。 */
    public String getProvinceCityJson() {
        return Json.encodeForScriptData(ProvinceCityCountyData.provinceCityData());
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
            for (Map<String, Object> row : ShengShiGridData.textValueRows()) {
                copy.add(new LinkedHashMap<>(row));
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
