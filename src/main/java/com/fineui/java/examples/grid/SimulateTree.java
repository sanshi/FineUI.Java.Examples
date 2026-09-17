package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.SimulateTreeData;

/** 模拟树列（路由 {@code grid/simulate-tree}）：按 TreeLevel 缩进渲染地区层级（展示型）。 */
@FineUIPage("grid/simulate-tree")
public class SimulateTree extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(SimulateTreeData.rows());
            Grid1.dataBind();
        }
    }
}
