package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.GroupFieldGridData;
import com.fineui.java.examples.code.PageBase;

/**
 * 多表头 + 显示/隐藏分组列（路由 {@code grid/group-field-hidden}）：初始隐藏[河南省/驻马店市]分组列，
 * 两个按钮分别切换[安徽省]、[驻马店市]分组列的显隐（列显隐态不回带，用客户端切换语义）。
 */
@FineUIPage("grid/group-field-hidden")
public class GroupFieldHidden extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(GroupFieldGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Button3_Click(Object sender, EventArgs e) {
        Grid1.toggleColumn("anhui");
    }

    public void Button1_Click(Object sender, EventArgs e) {
        Grid1.toggleColumn("zhumadian");
    }
}
