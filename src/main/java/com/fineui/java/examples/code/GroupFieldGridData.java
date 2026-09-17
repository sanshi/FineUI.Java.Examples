package com.fineui.java.examples.code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 多表头（GroupField/GroupFieldSort/GroupFieldHidden 三页共用）演示数据：10 行、各省市统计数值。
 * 字段：Id/Year/HZData1/HZData2/HLData1/HLData2/AHData1/AHData2/LogTime。数值取确定值（便于验证稳定）。
 */
public final class GroupFieldGridData {

    private GroupFieldGridData() {
    }

    /** 全部 10 行数据（Id=100..109，Year=2000..2009，LogTime 为当年 9 月 1 日）。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int year = 2000 + i;
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("Id", 100 + i);
            row.put("Year", year);
            row.put("HZData1", cell(i, 3));
            row.put("HZData2", cell(i, 5));
            row.put("HLData1", cell(i, 7));
            row.put("HLData2", cell(i, 11));
            row.put("AHData1", cell(i, 13));
            row.put("AHData2", cell(i, 17));
            row.put("LogTime", LocalDate.of(year, 9, 1));
            list.add(row);
        }
        return list;
    }

    // 确定性伪随机数（1000..9998），行索引与列质数因子混合，视觉上各不相同、又可稳定复现。
    private static int cell(int i, int prime) {
        return 1000 + ((i * prime * 631 + prime * 97) % 8999);
    }
}
