package com.fineui.java.examples.grideditor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 数字编辑框（初始为空）示例专用数据：5 个年份的各项费用，其中年份 [2017] 和 [2018] 的所有费用初始为空（null）。
 */
final class NumberBoxEmptyGridData {

    private NumberBoxEmptyGridData() {
    }

    static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        add(list, 101, 2014, 1000, 3000, 2000, 1000);
        add(list, 102, 2015, 1200, 3200, 2200, 1200);
        add(list, 103, 2016, 1500, 3500, 2500, 1500);
        add(list, 104, 2017, null, null, null, null);
        add(list, 105, 2018, null, null, null, null);
        return list;
    }

    private static void add(List<Map<String, Object>> list, int id, int year,
                            Integer xueFei, Integer zhusuFei, Integer huoshiFei, Integer shubenFei) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Year", year);
        row.put("XueFei", xueFei);
        row.put("ZhusuFei", zhusuFei);
        row.put("HuoshiFei", huoshiFei);
        row.put("ShubenFei", shubenFei);
        list.add(row);
    }
}
