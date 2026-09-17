package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridRowEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Window;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 表格弹出 IFrame 窗体（路由 {@code grid/iframe}）：既可点「编辑」窗口列命令弹出编辑窗体（声明式弹窗列，纯客户端），
 * 也可双击行由服务端读取数据键后弹出窗体。
 */
@FineUIPage("grid/iframe")
public class IFrame extends PageBase {

    Grid Grid1;
    Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Grid1_RowDoubleClick(Object sender, GridRowEventArgs e) {
        List<Object[]> dataKeys = Grid1.getDataKeys();
        int rowIndex = e.getRowIndex();
        if (rowIndex < 0 || rowIndex >= dataKeys.size()) {
            return;
        }
        Object[] keys = dataKeys.get(rowIndex);
        String name = String.valueOf(keys[1]);
        String url = "/grid/iframe-window?id=" + keys[0]
                + "&name=" + URLEncoder.encode(name, StandardCharsets.UTF_8);
        Window1.show(url, "编辑 - " + name);
    }

    public void Window1_Close(Object sender, EventArgs e) {
        showAlert("触发了窗体的关闭事件！");
    }
}
