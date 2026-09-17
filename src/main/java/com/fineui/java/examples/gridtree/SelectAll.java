package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 级联全选（路由 {@code grid-tree/select-all}）：以行选中（enable-check-box-select + keep-current-selection）
 * 配合客户端 rowselect/rowdeselect 监听，勾选/取消父节点时递归级联其所有子孙。默认展开 basic、res。
 */
@FineUIPage("grid-tree/select-all")
public class SelectAll extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.all());
            Grid1.dataBind();
        }
    }
}
