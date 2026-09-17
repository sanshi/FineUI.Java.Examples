package com.fineui.java.examples.toolbar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.MenuHyperLink;
import com.fineui.java.core.enums.Icon;
import com.fineui.java.examples.code.PageBase;

/**
 * 菜单项图标演示页（路由 {@code toolbar/menu-icon}）：工具栏按钮的下拉菜单项带图标；
 * 下方按钮在服务端删除/修改指定菜单项的图标。
 */
@FineUIPage("toolbar/menu-icon")
public class MenuIcon extends PageBase {

    MenuHyperLink MenuHyperLink1;
    MenuHyperLink MenuHyperLink2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 删除/恢复[化学与材料科学学院]菜单项的图标。 */
    public void Button1_Click(Object sender, EventArgs e) {
        if (MenuHyperLink1.getIcon() == Icon.Accept) {
            MenuHyperLink1.setIcon(Icon.None);
        } else {
            MenuHyperLink1.setIcon(Icon.Accept);
        }
    }

    /** 在两个图标间切换[管理学院]菜单项的图标。 */
    public void Button2_Click(Object sender, EventArgs e) {
        if (MenuHyperLink2.getIcon() == Icon.Accept) {
            MenuHyperLink2.setIcon(Icon.Application);
        } else {
            MenuHyperLink2.setIcon(Icon.Accept);
        }
    }
}
