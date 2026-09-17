package com.fineui.java.examples.code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 复杂多表头（GroupFieldComplex）演示数据：10 行、三级嵌套 + 同级混合叶子列所需字段。
 * 字段：Id/Year/HSData/HFData/HF_SHData/HF_BHData/LHData/ZMDData/ZMD_SPData/ZMD_XPData/LogTime。
 */
public final class GroupFieldComplexGridData {

    private GroupFieldComplexGridData() {
    }

    /** 全部 10 行数据。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int year = 2000 + i;
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("Id", 100 + i);
            row.put("Year", year);
            row.put("HSData", cell(i, 3));
            row.put("HFData", cell(i, 5));
            row.put("HF_SHData", cell(i, 7));
            row.put("HF_BHData", cell(i, 11));
            row.put("LHData", cell(i, 13));
            row.put("ZMDData", cell(i, 17));
            row.put("ZMD_SPData", cell(i, 19));
            row.put("ZMD_XPData", cell(i, 23));
            row.put("LogTime", LocalDate.of(year, 9, 1));
            list.add(row);
        }
        return list;
    }

    private static int cell(int i, int prime) {
        return 1000 + ((i * prime * 631 + prime * 97) % 8999);
    }
}
