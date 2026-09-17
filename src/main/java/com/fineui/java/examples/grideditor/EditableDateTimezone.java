package com.fineui.java.examples.grideditor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期列编辑（重选同一天不标记为已修改）：表格设置 {@code store-date-as-string="false"}，单元格里存的是 Date 对象。
 * 演示数据的计划日期故意以带时区偏移的 UTC ISO 字符串下发；日期列的脏值判定按列的显示格式（只到「天」）比较，
 * 用鼠标重选「同一天」不会被误标为已修改。
 */
@FineUIPage("grid-editor/editable-date-timezone")
public class EditableDateTimezone extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(sourceData());
            Grid1.dataBind();
        }
    }

    // 构造演示数据：PlanDate 故意使用带 +00:00 偏移的 UTC ISO 字符串，
    // 在东八区客户端会被 new Date(...) 解析为本地 08:00，用于演示「重选同一天不再被标记为已修改」。
    private List<Map<String, Object>> sourceData() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(row(1, "张三", "2026-06-19T00:00:00+00:00"));
        list.add(row(2, "李四", "2026-02-28T00:00:00+00:00"));
        list.add(row(3, "王五", "2026-12-01T00:00:00+00:00"));
        return list;
    }

    private static Map<String, Object> row(int id, String name, String planDate) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Name", name);
        row.put("PlanDate", planDate);
        return row;
    }
}
