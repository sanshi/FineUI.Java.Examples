package com.fineui.java.examples.code;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 大数据量数据库分页演示数据（999 行，字段 Id + EntranceTime）：仅按页生成当前页，模拟数据库分页只取一页。
 * 供序号列宽度/对齐示例（页大小 10）演示切换分页时序号连续。
 */
public final class LargeGridData {

    private static final int TOTAL = 999;
    private static final LocalDateTime BASE = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

    private LargeGridData() {
    }

    /** 总记录数（999）。 */
    public static int count() {
        return TOTAL;
    }

    /** 只生成当前页（Id 从 1000 起，EntranceTime 逐行加 1 秒；越界安全裁剪）。 */
    public static List<Map<String, Object>> paged(int pageIndex, int pageSize) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (pageSize <= 0) {
            return list;
        }
        int from = Math.max(0, pageIndex * pageSize);
        int to = Math.min(TOTAL, from + pageSize);
        for (int i = from; i < to; i++) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("Id", 1000 + i);
            row.put("EntranceTime", BASE.plusSeconds(i));
            list.add(row);
        }
        return list;
    }
}
