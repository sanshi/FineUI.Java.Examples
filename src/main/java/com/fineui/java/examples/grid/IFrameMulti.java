package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 多选行后弹出 IFrame 窗体（行命令 + 工具栏批量审批，纯客户端）（路由 {@code grid/iframe-multi}）。 */
@FineUIPage("grid/iframe-multi")
public class IFrameMulti extends PageBase {

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
