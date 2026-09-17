package com.fineui.java.examples.gridmerge;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 列合并（Columns/ColumnsAll/ColumnsDepends/ColumnsDependsFirst）专用数据：12 行，Major/Group 与主表一致，
 * 但 LogTime 精心编排出相邻同值段——使「依赖前列 / 依赖首列」的合并区间可稳定复现（示例用例期望）：
 * 注册日期段：[102-104] 同、[105-106] 同、[108-110] 同，其余唯一。
 */
public final class MergeColumnsData {

    private MergeColumnsData() {
    }

    /** 12 行列合并演示数据。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        //   id,   姓名,      性别, 入学年份, 在校,  专业,                分组, 注册日期年
        add(list, 101, "张萍萍", 0, 2000, true, "材料科学与工程系", 1, 2001);
        add(list, 102, "陈飞", 1, 2000, false, "化学系", 1, 2000);
        add(list, 103, "董婷婷", 0, 2000, true, "化学系", 2, 2000);
        add(list, 104, "刘国", 1, 2020, false, "化学系", 2, 2000);
        add(list, 105, "康颖颖", 0, 2008, true, "数学系", 3, 2008);
        add(list, 106, "彭博", 1, 2008, true, "数学系", 3, 2008);
        add(list, 107, "黄婷婷", 0, 2008, true, "数学系", 3, 2009);
        add(list, 108, "唐超", 1, 2020, false, "物理系", 3, 2020);
        add(list, 109, "杨婷婷", 0, 2020, true, "物理系", 5, 2020);
        add(list, 110, "徐鹏", 1, 2020, false, "物理系", 5, 2020);
        add(list, 111, "董国", 1, 2020, true, "自动化系", 5, 2006);
        add(list, 112, "张三石", 1, 2012, true, "材料科学与工程系", 5, 2012);
        return list;
    }

    private static void add(List<Map<String, Object>> list, int id, String name, int gender,
                            int entranceYear, boolean atSchool, String major, int group, int logYear) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Name", name);
        row.put("Gender", gender);
        row.put("EntranceYear", entranceYear);
        row.put("AtSchool", atSchool);
        row.put("Major", major);
        row.put("Group", group);
        row.put("LogTime", LocalDate.of(logYear, 9, 1));
        list.add(row);
    }
}
