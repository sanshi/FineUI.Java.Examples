package com.fineui.java.examples.gridother;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

/**
 * 长整型精度（路由 {@code grid-other/long-precision}）：演示超大整型 Id 在表格中的展示与精度丢失。
 *
 * <p>两个表格绑定同一份数据：表格一用默认配置，框架在数据绑定时把长整型 Id 转成字符串下发；
 * 表格二在模板上设 {@code convert-long-to-string="false"}（等价于服务端 {@code Grid2.setConvertLongToString(false)}），
 * Id 以 JSON 数字下发，超过 JavaScript 安全整数范围的部分会被舍入。三级开关见 {@code GridConfig}。
 */
@FineUIPage("grid-other/long-precision")
public class LongPrecision extends PageBase {

    Grid Grid1;
    Grid Grid2;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            List<Map<String, Object>> data = getSimpleData();

            // 表格一：默认配置（全局配置项 fineui.grid.convert-long-to-string 为 true），长整型自动转字符串
            Grid1.setDataSource(data);
            Grid1.dataBind();

            // 表格二：模板上关闭了转换，长整型以 JSON 数字下发，浏览器读到的就是被舍入后的值
            Grid2.setDataSource(data);
            Grid2.dataBind();
        }
    }

    /** 构造 3 行数据：第一行的 Id 是超过 JavaScript 安全整数范围的 17 位长整型 */
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
        // 数据源里保持 long 原样：转不转字符串由框架按 convert-long-to-string 决定（默认转）
        row.put("Id", id);
        row.put("Name", name);
        row.put("EntranceYear", entranceYear);
        row.put("AtSchool", atSchool);
        row.put("Major", major);
        row.put("Gender", gender);
        row.put("EntranceDate", entranceDate);
        list.add(row);
    }
}
