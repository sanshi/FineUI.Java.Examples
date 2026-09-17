package com.fineui.java.examples.griddynamic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 动态列排序示例的「班级」演示数据（Id/Name/EntranceYear/LogTime/Desc）。 */
public final class ClassGridData {

    private ClassGridData() {
    }

    /** 班级数据。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        add(list, 101, "班级一", 2000, "班级一创建于 2000 年 9 月，连续三年获得全校优秀班级称号。");
        add(list, 102, "班级二", 2005, "班级二创建于 2005 年 9 月，连续两年获得全校优秀班级称号。");
        add(list, 103, "班级三", 2008, "班级三创建于 2008 年 9 月，社团活动丰富、学风优良。");
        add(list, 104, "班级四", 2012, "班级四创建于 2012 年 9 月，多次在学科竞赛中取得优异成绩。");
        return list;
    }

    private static void add(List<Map<String, Object>> list, int id, String name, int year, String desc) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Name", name);
        row.put("EntranceYear", year);
        row.put("LogTime", LocalDate.of(year, 9, 1));
        row.put("Desc", desc);
        list.add(row);
    }
}
