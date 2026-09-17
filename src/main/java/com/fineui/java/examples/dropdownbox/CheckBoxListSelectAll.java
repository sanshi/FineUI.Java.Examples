package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.Arrays;

/**
 * 下拉复选框列表（全选）演示页（路由 {@code drop-down-box/check-box-list-select-all}）：弹出面板顶部工具栏放「全选」/「清空」
 * 两个按钮，通过客户端脚本批量勾选/取消勾选列表项后再同步进下拉框；「获取下拉框的选中值」回发读取当前文本与值。
 */
@FineUIPage("drop-down-box/check-box-list-select-all")
public class CheckBoxListSelectAll extends PageBase {

    DropDownBox DropDownBox1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
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
        // 后台更新下拉框的值，需要同时设置文本和值
        DropDownBox1.setTexts(Arrays.asList("PHP", "Basic"));
        DropDownBox1.setValues(Arrays.asList("php", "basic"));
    }
}
