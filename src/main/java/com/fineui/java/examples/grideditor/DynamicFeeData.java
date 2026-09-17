package com.fineui.java.examples.grideditor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 「动态创建可编辑列」示例的收费项目数据：每行 {@code Id}/{@code Name}/{@code YearData}，
 * 其中 {@code YearData} 是一个 JSON 字符串（各年份的费用），初始为空、由保存逻辑按年份累积写入。
 */
public final class DynamicFeeData {

    private DynamicFeeData() {
    }

    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(row(101, "学费"));
        list.add(row(102, "住宿费"));
        list.add(row(103, "伙食费"));
        list.add(row(104, "书本费"));
        list.add(row(105, "网络费"));
        return list;
    }

    private static Map<String, Object> row(int id, String name) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("Id", id);
        m.put("Name", name);
        m.put("YearData", "");
        return m;
    }
}
