package com.fineui.java.examples.grideditor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 省市联动单元格编辑示例的演示数据（Id / Name / Gender / Sheng / Shi 五列）。
 *
 * <p>{@link #rows()} 存省市的名称；{@link #rowsCoded()} 存省市的代码（配合客户端脚本把代码转成名称显示）。
 */
public final class ShengShiGridData {

    private ShengShiGridData() {
    }

    /** 省市列存名称的演示数据。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(row(101, "张萍萍", 0, "河南", "驻马店市"));
        list.add(row(102, "陈飞", 1, "安徽", "合肥市"));
        list.add(row(103, "董婷婷", 0, "北京", "北京市"));
        list.add(row(104, "刘国", 0, "", ""));
        list.add(row(105, "康颖颖", 0, "", ""));
        list.add(row(106, "彭博", 1, "", ""));
        return list;
    }

    /** 省市列存代码的演示数据（如 001 / 001001）。 */
    public static List<Map<String, Object>> rowsCoded() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(row(101, "张萍萍", 0, "001", "001001"));
        list.add(row(102, "陈飞", 1, "002", "002003"));
        list.add(row(103, "董婷婷", 0, "007", "007001"));
        list.add(row(104, "刘国", 0, "", ""));
        list.add(row(105, "康颖颖", 0, "", ""));
        list.add(row(106, "彭博", 1, "", ""));
        return list;
    }

    private static Map<String, Object> row(int id, String name, int gender, String sheng, String shi) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Name", name);
        row.put("Gender", gender);
        row.put("Sheng", sheng);
        row.put("Shi", shi);
        return row;
    }
}
