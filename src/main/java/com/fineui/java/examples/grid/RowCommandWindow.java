package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import java.util.List;
import java.util.Map;

/** 行命令使用独立字段生成客户详情网址和窗体标题。 */
@FineUIPage("grid/row-command-window")
public class RowCommandWindow extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(List.of(
                Map.of("ID", 105, "Slug", "customer-105", "DisplayName", "张三"),
                Map.of("ID", 106, "Slug", "customer-106", "DisplayName", "李四")
            ));
            Grid1.dataBind();
        }
    }
}
