package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.FormRow;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 动态创建表单元素（路由 {@code form/form-dynamic}）：用户名文本框与性别下拉列表是服务端
 * 在 {@code Page_Load} 里动态创建并加入 {@code FormRow1} 的（不在模板声明中）——首屏构建一次，
 * 回发时客户端随 {@code __FSTATE} 回带、服务端据此重建控件树，故事件照常分派。
 */
@FineUIPage("form/form-dynamic")
public class FormDynamic extends PageBase {

    FormRow FormRow1;

    // 这两个控件是动态创建的，不会在模板中声明，只需要在此定义即可
    TextBox tbxUserName;
    DropDownList ddlGender;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        } else {
            // 动态控件的服务端事件映射不信任 __FSTATE，回发时必须由服务端重新声明。
            ddlGender.setOnSelectedIndexChanged("ddlGender_SelectedIndexChanged");
        }
    }

    private void loadData() {
        TextBox tbxUser = new TextBox();
        tbxUser.setId("tbxUserName");
        tbxUser.setLabel("用户名");
        tbxUser.setShowLabel(true);
        tbxUser.setShowRedStar(true);
        tbxUser.setRequired(true);
        tbxUser.setEmptyText("请输入用户名");
        FormRow1.addChild(tbxUser, "items");

        DropDownList ddl = new DropDownList();
        ddl.setId("ddlGender");
        ddl.setLabel("性别（回发事件）");
        ddl.addListItem("0", "男", true);
        ddl.addListItem("1", "女", true);
        ddl.setAutoSelectFirstItem(false);
        // 添加后台事件处理函数
        ddl.setOnSelectedIndexChanged("ddlGender_SelectedIndexChanged");
        FormRow1.addChild(ddl, "items");
    }

    public void ddlGender_SelectedIndexChanged(Object sender, EventArgs e) {
        showNotify("选择的性别：" + ddlGender.getText());
    }

    public void Button1_Click(Object sender, EventArgs e) {
        // 故意从动态控件读取普通属性 label：它只在首屏 Page_Load 设置，回发时必须由 __FSTATE 恢复。
        showNotify(tbxUserName.getLabel() + "：" + tbxUserName.getValue() + "  性别：" + ddlGender.getText());
    }
}
