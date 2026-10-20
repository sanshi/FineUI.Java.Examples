package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 多选标签（下拉复选框列表，用户输入值，初始值）演示页（路由 {@code drop-down-box/tags-check-box-list-enable-edit-default}）：
 * 下拉框多选、标签模式、可自行输入（enable-edit）。首屏设初始文本与值，其中用户自输入值以 {@code __USERINPUT} 前缀混入
 * 值集合、与文本一一对应（须原样保留、不当非法值过滤）；「选中[php,basic]」「更新为[用户输入值]」演示后台更新。
 */
@FineUIPage("drop-down-box/tags-check-box-list-enable-edit-default")
public class TagsCheckBoxListEnableEditDefault extends PageBase {

    DropDownBox DropDownBox1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 一个真实选项和一个自定义标签按位置配对，初始自定义值不会误认作列表项。
            DropDownBox1.setSelection(
                    List.of("js", "__USERINPUT_value1"),
                    List.of("JavaScript", "初始自定义值"));
        }
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownBox1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", text, String.join(", ", DropDownBox1.getValues())));
        } else {
            labResult.setText("下拉框为空");
        }
    }

    public void btnSelectItem6_Click(Object sender, EventArgs e) {
        DropDownBox1.setSelection(
                List.of("php", "basic"),
                List.of("PHP", "Basic"));
    }

    public void btnSetText_Click(Object sender, EventArgs e) {
        DropDownBox1.setUserInput("用户输入值");
    }

    public void btnSetCommaText_Click(Object sender, EventArgs e) {
        DropDownBox1.setUserInput("研发, 华东");
    }

    public void btnClearSelection_Click(Object sender, EventArgs e) {
        DropDownBox1.clearSelection();
    }
}
