package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期列（字符串数据，DateParseString）（路由 {@code grid/date-parse-string}）：
 * 入学日期列的数据是 {@code MM/dd/yyyy} 格式的字符串，用 {@code date-parse-string}
 * 指定解析格式，配合 Date 渲染器按 {@code yyyy/MM/dd} 输出。
 */
@FineUIPage("grid/date-parse-string")
public class DateParseString extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(loadData());
            Grid1.dataBind();
        }
    }

    /** 构造 6 行演示数据：入学日期为 {@code MM/dd/yyyy} 格式的字符串。 */
    private List<Map<String, Object>> loadData() {
        List<Map<String, Object>> list = new ArrayList<>();
        String[][] rows = {
                // Id 姓名 Gender EntranceYear AtSchool Major Group EntranceDate(MM/dd/yyyy)
                {"101", "陈萍萍", "0", "2000", "true", "计算机应用技术", "1", "09/01/2000"},
                {"102", "胡飞", "1", "2008", "true", "信息工程", "1", "09/01/2008"},
                {"103", "金婷婷", "0", "2001", "false", "会计学", "2", "09/01/2001"},
                {"104", "潘国", "1", "2008", "false", "国际经济与贸易", "2", "09/01/2008"},
                {"105", "吴颖颖", "0", "2002", "true", "市场营销", "3", "09/01/2002"},
                {"106", "张博", "1", "2003", "true", "财务管理", "3", "09/01/2003"},
        };
        for (String[] r : rows) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("Id", Integer.parseInt(r[0]));
            row.put("Name", r[1]);
            row.put("Gender", Integer.parseInt(r[2]));
            row.put("EntranceYear", Integer.parseInt(r[3]));
            row.put("AtSchool", Boolean.parseBoolean(r[4]));
            row.put("Major", r[5]);
            row.put("Group", Integer.parseInt(r[6]));
            row.put("LogTime", LocalDate.now().minusDays(100));
            row.put("EntranceDate", r[7]);
            list.add(row);
        }
        return list;
    }
}
