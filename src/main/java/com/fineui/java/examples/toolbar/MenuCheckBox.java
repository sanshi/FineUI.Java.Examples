package com.fineui.java.examples.toolbar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 菜单复选框演示页（路由 {@code toolbar/menu-check-box}）：工具栏按钮的下拉菜单里放复选项——「系统语言」组内单选互斥
 * （GroupName），「喜欢的站点」独立多选。勾选变化触发服务端事件刷新下方标签；另有客户端脚本读取当前勾选项。
 */
@FineUIPage("toolbar/menu-check-box")
public class MenuCheckBox extends PageBase {

    com.fineui.java.core.controls.MenuCheckBox MenuLangEnglish;
    com.fineui.java.core.controls.MenuCheckBox MenuLangZHCN;
    com.fineui.java.core.controls.MenuCheckBox MenuLangZHTW;
    com.fineui.java.core.controls.MenuCheckBox MenuSiteBaidu;
    com.fineui.java.core.controls.MenuCheckBox MenuSiteGoogle;
    com.fineui.java.core.controls.MenuCheckBox MenuSiteMicrosoft;
    Label labLangResult;
    Label labSiteResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            updateLangResult();
            updateSiteResult();
        }
    }

    private void updateLangResult() {
        String selected = "";
        if (MenuLangEnglish.isChecked()) {
            selected = "English";
        } else if (MenuLangZHCN.isChecked()) {
            selected = "简体中文";
        } else if (MenuLangZHTW.isChecked()) {
            selected = "繁體中文";
        }
        labLangResult.setText("你选择的语言：" + selected);
    }

    private void updateSiteResult() {
        StringBuilder sites = new StringBuilder();
        if (MenuSiteBaidu.isChecked()) {
            sites.append("baidu.com, ");
        }
        if (MenuSiteGoogle.isChecked()) {
            sites.append("google.com, ");
        }
        if (MenuSiteMicrosoft.isChecked()) {
            sites.append("microsoft.com, ");
        }
        String result = sites.toString();
        if (result.endsWith(", ")) {
            result = result.substring(0, result.length() - 2);
        }
        labSiteResult.setText("你选择的站点：" + result);
    }

    /** 语言勾选变化时刷新语言结果标签。 */
    public void MenuLang_CheckedChanged(Object sender, EventArgs e) {
        updateLangResult();
    }

    /** 站点勾选变化时刷新站点结果标签。 */
    public void MenuSite_CheckedChanged(Object sender, EventArgs e) {
        updateSiteResult();
    }
}
