package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字输入框·星级评分演示页（路由 {@code form/number-box-rate}）：DisplayType=Rate 把数字渲染为星级评分，
 * 支持半星、只读、禁用点击清除、自定义字符/图标/个数、评分文本渲染；点击星星客户端即时改分，服务端亦可更新分值。
 */
@FineUIPage("form/number-box-rate")
public class NumberBoxRate extends PageBase {

    NumberBox NumberBox12;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button1_Click(Object sender, EventArgs e) {
        int currentNum = Integer.parseInt(NumberBox12.getValue());

        currentNum++;
        if (currentNum > 5) {
            currentNum = 0;
        }

        NumberBox12.setValue(String.valueOf(currentNum));
    }
}
