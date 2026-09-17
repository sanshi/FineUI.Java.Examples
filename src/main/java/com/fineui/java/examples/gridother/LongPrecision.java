package com.fineui.java.examples.gridother;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

/** 长整型精度（路由 {@code grid-other/long-precision}）：演示超大整型 Id 在表格中的展示。 */
@FineUIPage("grid-other/long-precision")
public class LongPrecision extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(getSimpleData());
            Grid1.dataBind();
        }
    }

    /** 构造 3 行数据；第 1 行 Id 为超大长整型（超过 JS 安全整数范围，用于演示精度问题）。 */
    private static List<Map<String, Object>> getSimpleData() {
        List<Map<String, Object>> list = new ArrayList<>();
        // 参数顺序：Id, Name, EntranceYear, AtSchool, Major, Gender, EntranceDate
        add(list, 21956392701267968L, "张萍萍", 2000, true, "材料科学与工程系", 0, "2000-09-01");
        add(list, 102L, "陈飞", 2000, false, "化学系", 1, "2001-09-01");
        add(list, 103L, "董婷婷", 2000, true, "化学系", 0, "2008-09-01");
        return list;
    }

    private static void add(List<Map<String, Object>> list, long id, String name, int entranceYear, boolean atSchool,
                            String major, int gender, String entranceDate) {
        Map<String, Object> row = new LinkedHashMap<>();
        // 以字符串下发超大整型 Id：JSON 数字到 JS 会用 Number 承载，超过安全整数范围（2^53-1）会被舍入丢精度，
        // 字符串则原样保留完整数字（这正是本示例要演示的长整型精度问题的正确处理）
        row.put("Id", String.valueOf(id));
        row.put("Name", name);
        row.put("EntranceYear", entranceYear);
        row.put("AtSchool", atSchool);
        row.put("Major", major);
        row.put("Gender", gender);
        row.put("EntranceDate", entranceDate);
        list.add(row);
    }
}
