package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 复选框列头自定义菜单（路由 {@code grid/check-all-custom-head-menu}）：把全选列头替换为「全选图标 + 下拉箭头」，
 * 点击箭头弹出独立浮动菜单，提供反选 / 选奇偶行 / 按条件选中等客户端批量选择。
 */
@FineUIPage("grid/check-all-custom-head-menu")
public class CheckAllCustomHeadMenu extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();

        // 自定义全选列头：全选复选框图标 + 一个下拉箭头（点击箭头弹出批量选择菜单）
        Grid1.setCheckBoxSelectHeaderTextRawHtml(new RawHtml(
                "<i class=\"f-icon f-iconfont f-grid-checkbox f-checkbox\"></i>"
                        + "<i class=\"f-icon f-iconfont f-iconfont-arrow-down custom-arrow-down\"></i>"));
    }
}
