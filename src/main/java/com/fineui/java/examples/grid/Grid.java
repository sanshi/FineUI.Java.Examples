package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 基础表格（路由 {@code grid/grid}）：数据绑定 + 列渲染 + 自定义渲染函数 + 日期格式化。
 * 列在模板里用 {@code <f:columns>} 声明（序号列、姓名、性别、入学年份、是否在校、专业、分组、注册日期），
 * 数据在 {@code Page_Load} 里绑定。
 */
@FineUIPage("grid/grid")
public class Grid extends PageBase {

    // 字段名与模板 id 一致；用全限定名避免与本页面类名 Grid 冲突。
    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 内存数据：首屏绑定一次，数据由客户端保管，回发无需重绑。
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
