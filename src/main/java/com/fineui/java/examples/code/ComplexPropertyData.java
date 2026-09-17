package com.fineui.java.examples.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 「绑定到二级属性」示例数据：每行含嵌套的 {@code Info} 对象（老师信息），列用点号路径 {@code Info.UserName} 取值。
 * 字段：Id/MyText(姓名)/Year(入学年份)/MyCheckBox(是否在校)/Info{UserName,ID}。
 */
public final class ComplexPropertyData {

    private ComplexPropertyData() {
    }

    /** 5 行学生数据（含嵌套老师信息）。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        add(list, 101, "陈萍萍", "2000", true, "张老师", "111");
        add(list, 102, "胡飞", "2008", false, "李老师", "222");
        add(list, 103, "金婷婷", "2001", true, "孙老师", "333");
        add(list, 104, "潘国", "2008", false, "黄老师", "444");
        add(list, 105, "吴颖颖", "2002", true, "郭老师", "555");
        return list;
    }

    private static void add(List<Map<String, Object>> list, int id, String myText, String year,
                            boolean myCheckBox, String teacherName, String teacherId) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("MyText", myText);
        row.put("Year", year);
        row.put("MyCheckBox", myCheckBox);
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("UserName", teacherName);
        info.put("ID", teacherId);
        row.put("Info", info);   // 二级属性：Info.UserName / Info.ID
        list.add(row);
    }
}
