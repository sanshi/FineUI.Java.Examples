package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.Arrays;

/**
 * 多选标签（下拉复选框列表）演示页（路由 {@code drop-down-box/tags-check-box-list}）：下拉框多选、标签模式，
 * 弹出面板内是复选框列表，勾选项以可删标签渲染进下拉框，初始值 {@code values=js,php} 反向勾选；
 * 「选中[php,basic]」按钮演示后台更新（须同时设置文本与值）；「获取下拉框的选中值」回发读取。
 */
@FineUIPage("drop-down-box/tags-check-box-list")
public class TagsCheckBoxList extends PageBase {

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
