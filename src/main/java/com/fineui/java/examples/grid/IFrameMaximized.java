package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 弹出最大化的 IFrame 窗体（声明式弹窗列 + 初始最大化）（路由 {@code grid/iframe-maximized}）。 */
@FineUIPage("grid/iframe-maximized")
public class IFrameMaximized extends PageBase {

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
