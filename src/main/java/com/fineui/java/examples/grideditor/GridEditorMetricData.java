package com.fineui.java.examples.grideditor;

import com.fineui.java.examples.code.StudentGridData;

import java.util.List;
import java.util.Map;

/**
 * 进度条 / 评分示例专用数据：在共用学生数据基础上，为每一行补充进度值（Progress1/Progress2）
 * 与评分值（Rate2/Rate3；Rate1 已在共用数据里）。返回的是每次新建的可变副本。
 */
final class GridEditorMetricData {

    private GridEditorMetricData() {
    }

    // {id, Progress1, Progress2}
    private static final int[][] PROGRESS = {
            {101, 30, 90}, {102, 60, 50}, {103, 65, 85}, {104, 95, 55}, {105, 30, 75}, {106, 0, 0},
            {107, 20, 80}, {108, 0, 60}, {109, 65, 30}, {110, 100, 80}, {111, 0, 0}, {112, 80, 75}
    };

    // {id, Rate2, Rate3}
    private static final Object[][] RATE = {
            {101, 5, 3.5}, {102, 2, 2.5}, {103, 0, 1.5}, {104, 1, 4.0}, {105, 0, 0.0}, {106, 2, 5.0},
            {107, 5, 0.0}, {108, 3, 4.0}, {109, 0, 2.5}, {110, 3, 3.0}, {111, 2, 3.5}, {112, 4, 5.0}
    };

    static List<Map<String, Object>> progressRows() {
        List<Map<String, Object>> rows = StudentGridData.rows();
        for (Map<String, Object> row : rows) {
            int id = idOf(row);
            for (int[] p : PROGRESS) {
                if (p[0] == id) {
                    row.put("Progress1", p[1]);
                    row.put("Progress2", p[2]);
                    break;
                }
            }
        }
        return rows;
    }

    static List<Map<String, Object>> rateRows() {
        List<Map<String, Object>> rows = StudentGridData.rows();
        for (Map<String, Object> row : rows) {
            int id = idOf(row);
            for (Object[] r : RATE) {
                if ((int) r[0] == id) {
                    row.put("Rate2", r[1]);
                    row.put("Rate3", r[2]);
                    break;
                }
            }
        }
        return rows;
    }

    private static int idOf(Map<String, Object> row) {
        return Integer.parseInt(String.valueOf(row.get("Id")));
    }
}
