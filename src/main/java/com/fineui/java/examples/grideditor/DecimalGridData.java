package com.fineui.java.examples.grideditor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 小数成绩单元格编辑示例专用数据：5 名学生的语文/数学成绩，其中 [刘国] 与 [康颖颖] 的成绩初始为空（null）。
 */
final class DecimalGridData {

    private DecimalGridData() {
    }

    static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        add(list, 101, "张萍萍", 0, "材料科学与工程系", 80.0, 90.0);
        add(list, 102, "陈飞", 1, "化学系", 85.8, 90.2);
        add(list, 103, "董婷婷", 0, "化学系", 90.5, 90.5);
        add(list, 104, "刘国", 1, "化学系", null, null);
        add(list, 105, "康颖颖", 0, "数学系", null, null);
        return list;
    }

    private static void add(List<Map<String, Object>> list, int id, String name, int gender,
                            String major, Double chineseScore, Double mathScore) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Name", name);
        row.put("Gender", gender);
        row.put("Major", major);
        row.put("ChineseScore", chineseScore);
        row.put("MathScore", mathScore);
        list.add(row);
    }
}
