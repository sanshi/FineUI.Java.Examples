package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 自定义列渲染函数 + 行内按钮点击弹出 IFrame 窗体（纯客户端）（路由 {@code grid/iframe-crc}）。 */
@FineUIPage("grid/iframe-crc")
public class IFrameCrc extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Window1_Close(Object sender, EventArgs e) {
        showAlert("触发了窗体的关闭事件！");
    }
}
