package com.fineui.java.examples.gridother;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

/** 数字格式列尾零省略（路由 {@code grid-other/grid-number-trailing-zero}）：数字渲染时省略末尾多余的零。 */
@FineUIPage("grid-other/grid-number-trailing-zero")
public class GridNumberTrailingZero extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(getExtendedData());
            Grid1.dataBind();
        }
    }

    /**
     * 构造含数字格式列的扩展数据（12 行，用于演示尾零省略效果）。
     * 部分行刻意设为整数或一位小数（如 9800.50、8500.00、2.00），以便直观对比尾零省略。
     */
    private static List<Map<String, Object>> getExtendedData() {
        List<Map<String, Object>> list = new ArrayList<>();
        // 参数顺序：Id, Name, EntranceYear, AtSchool, Major, Gender, EntranceDate,
        //           Salary（工资）, GPA（绩点）, AttendanceRate（出勤率）, ResearchFund（科研经费）
        add(list, 101, "张萍萍", 2000, true, "材料科学与工程系", 0, "2000-09-01", 12500.75, 3.75, 0.9567, 2565000.0);
        add(list, 102, "陈飞", 2000, false, "化学系", 1, "2001-09-01", 9800.50, 3.25, 0.8923, 1500000.0);
        add(list, 103, "董婷婷", 2000, true, "化学系", 0, "2008-09-01", 15600.25, 3.92, 0.9876, 3850000.0);
        add(list, 104, "刘国", 2020, false, "化学系", 1, "2020-09-01", 8500.00, 2.00, 0.75, 8500000.0);
        add(list, 105, "康颖颖", 2008, true, "数学系", 0, "2008-09-01", 14200.60, 3.68, 0.9345, 2950000.0);
        add(list, 106, "彭博", 2008, true, "数学系", 1, "2003-09-01", 16800.90, 3.45, 0.9123, 4520000.0);
        add(list, 107, "黄婷婷", 2008, true, "数学系", 0, "2000-09-01", 13200.30, 3.78, 0.9678, 3120000.0);
        add(list, 108, "唐超", 2020, false, "物理系", 1, "2020-09-01", 9200.00, 2.00, 0.82, 1200000.0);
        add(list, 109, "杨婷婷", 2020, true, "物理系", 0, "2003-09-01", 11800.80, 3.35, 0.8956, 2680000.0);
        add(list, 110, "徐鹏", 2020, false, "物理系", 1, "2020-09-01", 10500.20, 3.15, 0.8679, 1950000.0);
        add(list, 111, "董国", 2020, true, "自动化系", 1, "2006-09-01", 15200.55, 3.82, 0.9456, 3850000.0);
        add(list, 112, "张三石", 2012, true, "材料科学与工程系", 1, "2000-09-01", 17800.40, 3.28, 0.9123, 4850000.0);
        return list;
    }

    private static void add(List<Map<String, Object>> list, int id, String name, int entranceYear, boolean atSchool,
                            String major, int gender, String entranceDate, double salary, double gpa,
                            double attendanceRate, double researchFund) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Name", name);
        row.put("EntranceYear", entranceYear);
        row.put("AtSchool", atSchool);
        row.put("Major", major);
        row.put("Gender", gender);
        row.put("EntranceDate", entranceDate);
        row.put("Salary", salary);
        row.put("GPA", gpa);
        row.put("AttendanceRate", attendanceRate);
        row.put("ResearchFund", researchFund);
        list.add(row);
    }
}
