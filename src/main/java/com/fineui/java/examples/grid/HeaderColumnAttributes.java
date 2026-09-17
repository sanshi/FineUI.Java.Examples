package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 标题栏样式（表格列的 Attributes 属性，路由 {@code grid/header-column-attributes}）：给列头元素加自定义属性，
 * 由 CSS 据该属性设置样式（此处 {@code data-header-color=color1} → 加粗）。列头属性在模板的
 * {@code <f:attribute>} 标签声明（姓名、性别两列），后台只负责绑定数据。
 */
@FineUIPage("grid/header-column-attributes")
public class HeaderColumnAttributes extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
