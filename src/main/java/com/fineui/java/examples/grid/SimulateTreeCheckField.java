package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.SimulateTreeData;

/**
 * 模拟树列（全选反选，路由 {@code grid/simulate-tree-check-field}）：地区层级配可交互复选框列，
 * 客户端点父级复选框会级联选中/取消所有子级；初始默认选中「安徽省 合肥市 黄山市」三行。
 */
@FineUIPage("grid/simulate-tree-check-field")
public class SimulateTreeCheckField extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(SimulateTreeData.rows());
            Grid1.dataBind();
        }
    }
}
