package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字框触发器布局演示页（路由 {@code form/number-box-trigger-type}）：TriggerType=Stack（上下叠放）/Tile（平铺）/
 * Separate（左右分离），以及 ShowTrigger=false（不显示箭头）四种上下调整箭头布局。由真实 F.js 渲染、纯 JSON 回发。
 */
@FineUIPage("form/number-box-trigger-type")
public class NumberBoxTriggerType extends PageBase {

    NumberBox NumberBox1;
    NumberBox NumberBox2;
    NumberBox NumberBox3;
    NumberBox NumberBox4;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofFields(NumberBox1, NumberBox2, NumberBox3, NumberBox4));
    }
}
