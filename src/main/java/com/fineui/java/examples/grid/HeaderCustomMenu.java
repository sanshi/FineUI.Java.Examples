package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;

import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 自定义表头工具菜单（路由 {@code grid/header-custom-menu}）：关闭内置列头菜单，用标题栏工具图标弹出「管理列」
 * 显隐菜单；另演示服务端按数据键读取当前选中行。
 */
@FineUIPage("grid/header-custom-menu")
public class HeaderCustomMenu extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"result\"><tr><th>ID</th><th>姓名</th><th>性别</th><th>专业</th></tr>");

        for (Object[] keys : Grid1.getSelectedDataKeys()) {
            sb.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                    keys[0], keys[1],
                    Integer.parseInt(String.valueOf(keys[2])) == 1 ? "男" : "女",
                    keys[3]));
        }

        sb.append("</table>");
        showNotifyRaw(sb.toString());
    }
}
