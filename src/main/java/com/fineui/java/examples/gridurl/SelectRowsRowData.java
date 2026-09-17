package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网址数据源 · 选中多行并读取行数据（路由 {@code grid-data-url/select-rows-row-data}）：
 * 表格首屏按行 id 初始选中；「选中了哪些行」按钮在客户端读取选中行的字段数据并弹窗展示；
 * 「选中[陈飞]，[彭博]，[唐超]所在的行」按钮回发时，服务端按姓名查出对应行 id 并设为选中行。
 */
@FineUIPage("grid-data-url/select-rows-row-data")
public class SelectRowsRowData extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        // 首屏初始选中行由模板 selected-row-id-array 声明，选中行数据读取在客户端完成，服务端不绑定。
    }

    /** 按姓名从数据源匹配出对应行 id，设置为选中行。 */
    public void Button2_Click(Object sender, EventArgs e) {
        List<String> ids = new ArrayList<>();
        for (Map<String, Object> row : StudentGridData.rows()) {
            String name = String.valueOf(row.get("Name"));
            if ("陈飞".equals(name) || "彭博".equals(name) || "唐超".equals(name)) {
                ids.add(String.valueOf(row.get("Id")));
            }
        }
        Grid1.setSelectedRowIdArray(ids.toArray(new String[0]));
    }
}
