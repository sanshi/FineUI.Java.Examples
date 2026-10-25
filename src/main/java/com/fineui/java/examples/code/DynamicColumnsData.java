package com.fineui.java.examples.code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 动态换列的固定业务样本；两张表往返切换时读取相同编号、日期和统计数据。 */
public final class DynamicColumnsData {
    private DynamicColumnsData() {
    }

    public static List<Map<String, Object>> studentRows() {
        List<Map<String, Object>> rows = new ArrayList<>();
        for (Map<String, Object> student : StudentGridData.rows()) {
            Map<String, Object> row = new LinkedHashMap<>();
            for (String field : List.of("Id", "Name", "Gender", "EntranceYear", "AtSchool", "Major", "Group", "LogTime", "Desc")) {
                row.put(field, student.get(field));
            }
            int id = (Integer) row.get("Id");
            int daysAgo;
            if (id <= 104) {
                daysAgo = 100;
            } else if (id == 105) {
                daysAgo = 60;
            } else if (id <= 110) {
                daysAgo = 50;
            } else {
                daysAgo = 5;
            }
            row.put("LogTime", LocalDate.of(2020, 1, 1).minusDays(daysAgo).atStartOfDay());
            rows.add(row);
        }
        return rows;
    }

    public static List<Map<String, Object>> statisticsRows() {
        List<Map<String, Object>> rows = GroupFieldGridData.rows();
        for (Map<String, Object> row : rows) {
            row.put("LogTime", ((LocalDate) row.get("LogTime")).atStartOfDay());
        }
        return rows;
    }
}
