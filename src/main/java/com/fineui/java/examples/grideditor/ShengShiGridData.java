package com.fineui.java.examples.grideditor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 省市联动单元格编辑示例的演示数据（九列：Id / Name / Gender / EntranceYear / EntranceDate / AtSchool / Major / Sheng / Shi）。
 *
 * <p>{@link #rows()} 存省市的名称；{@link #rowsCoded()} 存省市的代码（配合客户端脚本把代码转成名称显示）。
 *
 * <p>本类被三个页面共用：{@link #rows()} 供 {@code grid-editor/sheng-shi} 与
 * {@code grid/form-window-cell-edit-sheng-shi}，{@link #rowsCoded()} 供 {@code grid-editor/sheng-shi-text-value}。
 * 其中 {@code grid/form-window-cell-edit-sheng-shi} 会把整行回填进弹窗表单，而表单里的入学年份、入学日期、
 * 所学专业都是必填项，所以这几列必须带值——否则回填后校验不通过，窗体里的「保存到表格」会被直接拦下。
 */
public final class ShengShiGridData {

    private ShengShiGridData() {
    }

    /** 省市列存名称的演示数据。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(row(101, "张萍萍", 0, 2000, "2000-09-01", true, "材料科学与工程系", "河南", "驻马店市"));
        list.add(row(102, "陈飞", 1, 2000, "2001-09-01", false, "化学系", "安徽", "合肥市"));
        list.add(row(103, "董婷婷", 0, 2000, "2008-09-01", true, "化学系", "北京", "北京市"));
        list.add(row(104, "刘国", 0, 2002, "2002-09-01", false, "化学系", "", ""));
        list.add(row(105, "康颖颖", 0, 2008, "2008-09-01", true, "数学系", "", ""));
        list.add(row(106, "彭博", 1, 2008, "2008-09-01", true, "数学系", "", ""));
        return list;
    }

    /** 省市列存代码的演示数据（如 001 / 001001）。 */
    public static List<Map<String, Object>> rowsCoded() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(row(101, "张萍萍", 0, 2000, "2000-09-01", true, "材料科学与工程系", "001", "001001"));
        list.add(row(102, "陈飞", 1, 2000, "2001-09-01", false, "化学系", "002", "002003"));
        list.add(row(103, "董婷婷", 0, 2000, "2008-09-01", true, "化学系", "007", "007001"));
        list.add(row(104, "刘国", 0, 2002, "2002-09-01", false, "化学系", "", ""));
        list.add(row(105, "康颖颖", 0, 2008, "2008-09-01", true, "数学系", "", ""));
        list.add(row(106, "彭博", 1, 2008, "2008-09-01", true, "数学系", "", ""));
        return list;
    }

    /** 参数顺序与表格列顺序一致，便于与页面上的列定义逐列对照。 */
    private static Map<String, Object> row(int id, String name, int gender, int entranceYear, String entranceDate,
            boolean atSchool, String major, String sheng, String shi) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Name", name);
        row.put("Gender", gender);
        row.put("EntranceYear", entranceYear);
        row.put("EntranceDate", entranceDate);
        row.put("AtSchool", atSchool);
        row.put("Major", major);
        row.put("Sheng", sheng);
        row.put("Shi", shi);
        return row;
    }
}
