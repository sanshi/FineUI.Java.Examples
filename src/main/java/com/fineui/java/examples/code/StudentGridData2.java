package com.fineui.java.examples.code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页示例共用的学生演示数据（22 行）：内存分页一次性全绑、数据库分页按页切片。
 * 字段与列的 {@code data-field} 一致（学号/姓名/性别/入学年份/是否在校/专业/分组/注册日期）。
 */
public final class StudentGridData2 {

    private StudentGridData2() {
    }

    // 学杂费 / 学费（[ExtraFee, Fee]），固定值（合计断言依赖精确值，勿随意改动）。
    private static final Map<Integer, int[]> FEE_BY_ID = new LinkedHashMap<>();
    // GPA，固定值。
    private static final Map<Integer, Double> GPA_BY_ID = new LinkedHashMap<>();

    static {
        int[][] fee = {
            {101, 299, 2990}, {102, 199, 1990}, {103, 299, 3990}, {104, 399, 3998},
            {105, 499, 4992}, {106, 99, 997}, {107, 399, 3995}, {108, 299, 2996},
            {109, 599, 5990}, {110, 699, 6990}, {111, 399, 3990}, {112, 299, 2999},
            {113, 399, 3996}, {114, 499, 4990}, {115, 299, 2992}, {116, 199, 1990},
            {117, 99, 990}, {118, 399, 3990}, {119, 399, 3991}, {120, 399, 3992},
            {121, 299, 2992}, {122, 399, 3993},
        };
        for (int[] r : fee) {
            FEE_BY_ID.put(r[0], new int[] {r[1], r[2]});
        }
        double[][] gpa = {
            {101, 3.60}, {102, 2.69}, {103, 2.72}, {104, 2.69}, {105, 2.50}, {106, 2.13},
            {107, 2.90}, {108, 2.69}, {109, 2.69}, {110, 2.69}, {111, 2.69}, {112, 2.69},
            {113, 2.69}, {114, 2.69}, {115, 2.69}, {116, 2.69}, {117, 2.69}, {118, 2.69},
            {119, 2.69}, {120, 2.69}, {121, 2.69}, {122, 2.69},
        };
        for (double[] r : gpa) {
            GPA_BY_ID.put((int) r[0], r[1]);
        }
    }

    /** 全部 22 行学生数据（每行一个字段字典）。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        add(list, 101, "陈萍萍", 0, 2000, true, "计算机应用技术", 1, "2000-09-01");
        add(list, 102, "胡飞", 1, 2008, true, "信息工程", 1, "2008-09-01");
        add(list, 103, "金婷婷", 0, 2001, false, "会计学", 2, "2001-09-01");
        add(list, 104, "潘国", 1, 2008, false, "国际经济与贸易", 2, "2008-09-01");
        add(list, 105, "吴颖颖", 0, 2020, true, "市场营销", 3, "2020-09-01");
        add(list, 106, "张博", 1, 2003, true, "财务管理", 3, "2003-09-01");
        add(list, 107, "杨倩倩", 0, 2000, false, "材料物理与化学", 4, "2000-09-01");
        add(list, 108, "董超", 1, 2020, false, "生物医学工程", 4, "2020-09-01");
        add(list, 109, "张娟娟", 0, 2003, true, "材料物理与化学", 5, "2003-09-01");
        add(list, 110, "叶鹏", 1, 2006, false, "电子商务", 5, "2006-09-01");
        add(list, 111, "李玲玲", 0, 2020, true, "管理学", 5, "2020-09-01");
        add(list, 112, "张萍萍", 0, 2000, true, "计算机应用技术", 1, "2000-09-01");
        add(list, 113, "曹飞", 1, 2008, false, "信息工程", 1, "2008-09-01");
        add(list, 114, "孙婷婷", 0, 2001, true, "材料物理与化学", 2, "2001-09-01");
        add(list, 115, "董国", 1, 2020, false, "国际经济与贸易", 2, "2008-09-01");
        add(list, 116, "习颖颖", 0, 2020, true, "市场营销", 3, "2020-09-01");
        add(list, 117, "李博", 1, 2003, true, "财务管理", 3, "2003-09-01");
        add(list, 118, "黄婷婷", 0, 2000, false, "材料物理与化学", 4, "2000-09-01");
        add(list, 119, "韩超", 1, 2020, false, "生物医学工程", 4, "2020-09-01");
        add(list, 120, "王娟娟", 0, 2003, true, "材料物理与化学", 5, "2003-09-01");
        add(list, 121, "周鹏", 1, 2006, false, "电子商务", 5, "2006-09-01");
        add(list, 122, "吴玲玲", 0, 2020, true, "管理学", 5, "2020-09-01");
        return list;
    }

    /** 总记录数（数据库分页设 recordCount 用）。 */
    public static int count() {
        return rows().size();
    }

    /** 按字段排序后的整表副本（客户端/服务端排序端点用）：{@code direction} 为 {@code DESC} 时降序；字段为空不排序。 */
    public static List<Map<String, Object>> sortedRows(String field, String direction) {
        List<Map<String, Object>> all = rows();
        GridSort.sortInPlace(all, field, direction);
        return all;
    }

    /** 取某一页数据（数据库分页：按 pageIndex/pageSize 切片，越界安全裁剪）。 */
    public static List<Map<String, Object>> paged(int pageIndex, int pageSize) {
        return slice(rows(), pageIndex, pageSize);
    }

    /** 先按字段排序、再取某一页（数据库分页 + 排序）。 */
    public static List<Map<String, Object>> pagedSorted(int pageIndex, int pageSize, String field, String direction) {
        List<Map<String, Object>> all = rows();
        GridSort.sortInPlace(all, field, direction);
        return slice(all, pageIndex, pageSize);
    }

    /** 先按多列排序、再取某一页（数据库分页 + 多列排序）：{@code sortFields} 为 {@code [字段,方向,...]}。 */
    public static List<Map<String, Object>> pagedSortedMulti(int pageIndex, int pageSize, String[] sortFields) {
        List<Map<String, Object>> all = rows();
        GridSort.sortInPlaceMulti(all, sortFields);
        return slice(all, pageIndex, pageSize);
    }

    private static List<Map<String, Object>> slice(List<Map<String, Object>> all, int pageIndex, int pageSize) {
        if (pageSize <= 0) {
            return new ArrayList<>(all);
        }
        int from = Math.max(0, pageIndex * pageSize);
        if (from >= all.size()) {
            return new ArrayList<>();
        }
        int to = Math.min(all.size(), from + pageSize);
        return new ArrayList<>(all.subList(from, to));
    }

    private static void add(List<Map<String, Object>> list, int id, String name, int gender,
                            int entranceYear, boolean atSchool, String major, int group, String logDate) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Name", name);
        row.put("Gender", gender);
        row.put("EntranceYear", entranceYear);
        row.put("AtSchool", atSchool);
        row.put("Major", major);
        row.put("Group", group);
        row.put("LogTime", LocalDate.parse(logDate));
        row.put("Desc", name + "，就读于" + major + "，于 " + entranceYear + " 年入学。");   // 行扩展列展示用
        // 学费 / 学杂费 / GPA（合计行示例用；固定值，使合计断言稳定复现）
        int[] fee = FEE_BY_ID.get(id);   // [ExtraFee, Fee]
        row.put("ExtraFee", fee[0]);
        row.put("Fee", fee[1]);
        row.put("GPA", GPA_BY_ID.get(id));
        // 考试成绩（行分组合计示例用；确定值，TotalScore = 语文 + 数学）
        int chinese = 80 + id % 20;
        int math = 60 + id % 40;
        row.put("ChineseScore", chinese);
        row.put("MathScore", math);
        row.put("TotalScore", chinese + math);
        // 体检结果（列锁定 + 合计等宽表格演示用；确定值，列非空即可）
        row.put("ShenGao", 150 + id % 40);
        row.put("TiZhong", 45 + id % 45);
        row.put("XueYaDi", 60 + id % 25);
        row.put("XueYaGao", 100 + id % 40);
        row.put("ShiLiZuo", 4.0 + (id % 12) / 10.0);
        row.put("ShiLiYou", 4.0 + (id % 10) / 10.0);
        list.add(row);
    }
}
