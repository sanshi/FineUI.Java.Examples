package com.fineui.java.examples.code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 表格示例共用的学生演示数据（学号/姓名/性别/入学年份/是否在校/专业/分组/注册日期）。 */
public final class StudentGridData {

    private StudentGridData() {
    }

    // 分组B（固定值：单元格合并「相邻同值」示例依赖精确分组，勿随意改动）。
    private static final Map<Integer, Integer> GROUP_B_BY_ID = new LinkedHashMap<>();
    // 爱好（逗号分隔，编辑框复选列预选用）。
    private static final Map<Integer, String> HOBBY_BY_ID = new LinkedHashMap<>();

    static {
        int[][] gb = {{101, 1}, {102, 3}, {103, 2}, {104, 2}, {105, 3}, {106, 3},
                {107, 3}, {108, 3}, {109, 5}, {110, 5}, {111, 5}, {112, 5}};
        for (int[] r : gb) {
            GROUP_B_BY_ID.put(r[0], r[1]);
        }
        String[] hobbies = {"reading,travel,music", "basketball,travel,movie,reading,music", "basketball,movie,music",
                "reading,basketball,movie", "reading,travel,movie,music", "basketball,travel,music",
                "travel,movie,music,reading", "reading,movie,music,basketball", "reading,basketball,movie",
                "reading,basketball,travel", "reading,movie,music,basketball", "reading,movie,music"};
        for (int i = 0; i < hobbies.length; i++) {
            HOBBY_BY_ID.put(101 + i, hobbies[i]);
        }
    }

    /** 12 行学生数据（每行一个字段字典，字段名与列的 {@code data-field} 一致）。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        add(list, 101, "张萍萍", 0, 2000, true, "材料科学与工程系", 1);
        add(list, 102, "陈飞", 1, 2000, false, "化学系", 1);
        add(list, 103, "董婷婷", 0, 2000, true, "化学系", 2);
        add(list, 104, "刘国", 1, 2020, false, "化学系", 2);
        add(list, 105, "康颖颖", 0, 2008, true, "数学系", 3);
        add(list, 106, "彭博", 1, 2008, true, "数学系", 3);
        add(list, 107, "黄婷婷", 0, 2008, true, "数学系", 3);
        add(list, 108, "唐超", 1, 2020, false, "物理系", 3);
        add(list, 109, "杨婷婷", 0, 2020, true, "物理系", 5);
        add(list, 110, "徐鹏", 1, 2020, false, "物理系", 5);
        add(list, 111, "董国", 1, 2020, true, "自动化系", 5);
        add(list, 112, "张三石", 1, 2012, true, "材料科学与工程系", 5);
        return list;
    }

    /**
     * 按字段排序后的数据副本（服务端排序示例用）：{@code direction} 为 {@code DESC} 时降序，否则升序；
     * {@code field} 为空则不排序。空值排最后。
     */
    public static List<Map<String, Object>> sortedRows(String field, String direction) {
        List<Map<String, Object>> list = rows();
        GridSort.sortInPlace(list, field, direction);
        return list;
    }

    /**
     * 按多列排序后的数据副本（多列排序示例用）：{@code sortFields} 为 {@code [字段,方向,字段,方向...]}，
     * 首个字段为主序、依次为次序；为空则不排序。
     */
    public static List<Map<String, Object>> sortedRowsMulti(String[] sortFields) {
        List<Map<String, Object>> list = rows();
        GridSort.sortInPlaceMulti(list, sortFields);
        return list;
    }

    private static void add(List<Map<String, Object>> list, int id, String name, int gender,
                            int entranceYear, boolean atSchool, String major, int group) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Name", name);
        row.put("Gender", gender);
        row.put("EntranceYear", entranceYear);
        row.put("AtSchool", atSchool);
        row.put("Major", major);
        row.put("Group", group);
        // 注册日期：相对今天的偏移天数（101-104:-100、105:-60、106-110:-50、111-112:-5），
        // 使表头过滤「日期范围」示例的行数保持稳定
        int logDaysAgo = id <= 104 ? 100 : (id == 105 ? 60 : (id <= 110 ? 50 : 5));
        row.put("LogTime", LocalDate.now().minusDays(logDaysAgo));
        // 个人简介：基础句 + 按 id 追加 0~3 段，使各行文本长度不同（自动换行时行高随之变化，供行高/换行示例演示）
        StringBuilder desc = new StringBuilder(name + "，就读于" + major + "，于 " + entranceYear + " 年入学，是一名勤奋好学、积极向上的学生。");
        for (int k = 0; k < id % 4; k++) {
            desc.append("平日里热爱阅读与运动，乐于助人，积极参加各类社团活动与志愿服务，深受师生好评。");
        }
        row.put("Desc", desc.toString());
        // 体检结果（列锁定等宽表格演示用；确定值，列非空即可，spec 不校验具体值）
        row.put("ShenGao", 150 + id % 40);
        row.put("TiZhong", 45 + id % 45);
        row.put("XueYaDi", 60 + id % 25);
        row.put("XueYaGao", 100 + id % 40);
        row.put("ShiLiZuo", 4.0 + (id % 12) / 10.0);
        row.put("ShiLiYou", 4.0 + (id % 10) / 10.0);
        // 考试成绩（多表头「考试成绩」分组 + 编辑框成绩录入示例用；TotalScore = 语文 + 数学）
        int chinese = 80 + id % 20;
        int math = 60 + id % 40;
        row.put("ChineseScore", chinese);
        row.put("MathScore", math);
        row.put("TotalScore", chinese + math);
        row.put("GroupB", GROUP_B_BY_ID.getOrDefault(id, group));   // 分组B（单元格合并/复选分组示例用；固定值，断言依赖）
        row.put("Hobby", HOBBY_BY_ID.getOrDefault(id, "reading,music"));   // 爱好（编辑框复选列示例用；逗号分隔预选）
        // 多列宽表格（行高/延迟渲染示例用；确定值，列非空即可，spec 不校验具体值）
        row.put("GUID", String.format("%08x-0000-4000-8000-%012d", id * 2654435761L & 0xffffffffL, id));
        row.put("EntranceDate", LocalDate.of(entranceYear, 9, 1) + " 08:30:00");   // 入学时间（含时分秒的文本）
        row.put("ShiLiZuoJiaoZhen", 4.8 + (id % 4) / 10.0);   // 矫正视力（左）
        row.put("ShiLiYouJiaoZhen", 4.8 + (id % 3) / 10.0);   // 矫正视力（右）
        // 卡片模式头像/状态（头像卡片示例用）：按性别使用项目自有头像 + 状态 1~4（优秀/良好/补考/重修）
        String avatarFolder = gender == 1 ? "male" : "female";
        row.put("Avatar", "/res/images/avatars/" + avatarFolder + "/" + (id % 12 + 1) + ".jpg");
        row.put("Status", id % 4 + 1);
        // 评分（1~5，仪表盘/评分列示例用）
        row.put("Rate1", id % 5 + 1);
        // 工资（货币逐位分格渲染示例用；确定值，spec 只校验渲染格数不校验数值）
        row.put("Salary", 5000 + id * 13.5);
        // 仅日期 / 仅时间（DateOnly/TimeOnly 字段示例用；预格式化为字符串，保证输出 yyyy-MM-dd 与 HH:mm:ss）
        row.put("DateOnly1", LocalDate.of(entranceYear, 9, 1).toString());
        row.put("TimeOnly1", String.format("%02d:%02d:%02d", 8 + id % 10, id % 60, id % 60));
        list.add(row);
    }

    /**
     * 100+ 行数据（行高/延迟渲染等大数据量示例用）：把 {@link #rows()} 的 12 行复制 9 组（共 108 行），
     * 行 id 从 101 顺序递增；第 2 组起在姓名后追加「（组号）」以示区分。
     */
    public static List<Map<String, Object>> rows100() {
        return rowsN(108);
    }

    /**
     * 生成指定行数的数据（延迟渲染 / 大数据量示例用）：循环复制 {@link #rows()} 的 12 行直到达到 {@code count} 行，
     * 行 id 从 101 顺序递增；第 2 组起在姓名后追加「（组号）」以示区分。
     */
    public static List<Map<String, Object>> rowsN(int count) {
        List<Map<String, Object>> base = rows();
        List<Map<String, Object>> list = new ArrayList<>();
        int newId = 101;
        int group = 0;
        while (list.size() < count) {
            for (Map<String, Object> src : base) {
                if (list.size() >= count) {
                    break;
                }
                Map<String, Object> row = new LinkedHashMap<>(src);
                row.put("Id", newId);
                if (group > 0) {
                    row.put("Name", src.get("Name") + "（" + group + "）");
                }
                list.add(row);
                newId++;
            }
            group++;
        }
        return list;
    }
}
