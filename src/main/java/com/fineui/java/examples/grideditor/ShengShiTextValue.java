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
 * 单元格编辑（下拉列表，省市联动，客户端获取省市名称）：与省市联动示例类似，但单元格保存的是省市的<b>代码</b>而非名称，
 * 显示时由客户端渲染函数据代码查名称。省（代码-名称）、省→市（代码-名称）两份数据经 {@link #getShengJson()} /
 * {@link #getShiJson()} 输出到页面脚本供客户端使用。
 */
@FineUIPage("grid-editor/sheng-shi-text-value")
public class ShengShiTextValue extends PageBase {

    private static final String SESSION_KEY = "GridEditor.ShengShiTextValue";
    private static final String[] COLUMNS = {"Name", "Gender", "Sheng", "Shi"};

    // 省（代码 - 名称）；代码作为 window._SHI 的键
    private static final String[][] SHENG = {
            {"001", "北京"}, {"002", "河南"}, {"003", "河北"}, {"004", "湖南"},
            {"005", "湖北"}, {"006", "广西"}, {"007", "安徽"}
    };

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
        ddlSheng.setDataSource(DataSourceUtil.SHENG);
        ddlSheng.dataBind();

        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    /** 省数据（代码-名称）的 JSON，供模板输出到 window._SHENG。结构如 {@code [["001","北京"],...]}。 */
    public String getShengJson() {
        List<List<String>> shengData = new ArrayList<>();
        for (String[] sheng : SHENG) {
            shengData.add(List.of(sheng[0], sheng[1]));
        }
        return Json.encode(shengData);
    }

    /** 省→市数据（代码-名称）的 JSON，供模板输出到 window._SHI。结构如 {@code {"002":[["002001","郑州市"],...]}}。 */
    public String getShiJson() {
        Map<String, List<List<String>>> shiData = new LinkedHashMap<>();
        for (String[] sheng : SHENG) {
            String shengValue = sheng[0];
            String shengText = sheng[1];
            List<String> cities = DataSourceUtil.SHI.getOrDefault(shengText, List.of());
            List<List<String>> newSheng = new ArrayList<>();
            int num = 1;
            for (String cityName : cities) {
                newSheng.add(List.of(shengValue + paddingLeft(num), cityName));
                num++;
            }
            shiData.put(shengValue, newSheng);
        }
        return Json.encode(shiData);
    }

    // 把数字左补 0 到 3 位（如 1 → "001"）
    private static String paddingLeft(int value) {
        String str = String.valueOf(value);
        StringBuilder sb = new StringBuilder();
        for (int i = 0, count = 3 - str.length(); i < count; i++) {
            sb.append("0");
        }
        sb.append(str);
        return sb.toString();
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
            for (Map<String, Object> row : ShengShiGridData.rowsCoded()) {
                copy.add(new LinkedHashMap<>(row));
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
