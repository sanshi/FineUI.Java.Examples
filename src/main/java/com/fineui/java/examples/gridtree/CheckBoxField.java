package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 复选框列（路由 {@code grid-tree/check-box-field}）：不用行复选框，改用复选框列（render-check-field，
 * render-as-static-field=false），并按类型（仅文件夹）显示复选框；行号列启用树形编号（enable-tree-number）。
 * 两个按钮均为纯客户端：查看选中行、列出勾选的目录。
 */
@FineUIPage("grid-tree/check-box-field")
public class CheckBoxField extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.all());
            Grid1.dataBind();
        }
    }
}
