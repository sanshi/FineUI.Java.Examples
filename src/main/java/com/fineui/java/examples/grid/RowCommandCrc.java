package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 手工渲染行内按钮 + 客户端事件（路由 {@code grid/row-command-crc}）：自定义列渲染函数出按钮，客户端点击弹通知/确认。 */
@FineUIPage("grid/row-command-crc")
public class RowCommandCrc extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
