package com.fineui.java.examples.gridother;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 显示隐藏列（路由 {@code grid-other/hide-column}）：性别列初始隐藏且不可经列头菜单显隐，可后台切换。 */
@FineUIPage("grid-other/hide-column")
public class HideColumn extends PageBase {

    Grid Grid1;
    HiddenField hfGenderHidden;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Button3_Click(Object sender, EventArgs e) {
        // 列是声明式静态结构、不回带，故用隐藏字段记住性别列当前显隐态（初始按声明为隐藏），据此双向切换
        boolean hidden = !"false".equals(hfGenderHidden.getText());   // 空/"true" 视为隐藏
        if (hidden) {
            Grid1.showColumn("Gender");
            hfGenderHidden.setText("false");
        } else {
            Grid1.hideColumn("Gender");
            hfGenderHidden.setText("true");
        }
    }
}
