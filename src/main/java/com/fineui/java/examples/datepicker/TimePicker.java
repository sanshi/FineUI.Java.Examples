package com.fineui.java.examples.datepicker;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/** 日期 + 时间选择器组合示例（路由 {@code date-picker/time-picker}）：EnableEdit=false 不可编辑、时间步长 30 分钟。 */
@FineUIPage("date-picker/time-picker")
public class TimePicker extends PageBase {

    protected DatePicker DatePicker1;
    // 字段类型用全限定名，避免与本示例页类名 TimePicker 冲突。
    protected com.fineui.java.core.controls.TimePicker TimePicker1;
    protected Button btnSubmit;
    protected Label labResult;

    protected Label labClickAction;

    public void btnToggleClickAction_Click(Object sender, EventArgs e) {
        DatePicker1.setEnableClickAction(!DatePicker1.isEnableClickAction());
        TimePicker1.setEnableClickAction(!TimePicker1.isEnableClickAction());
        labClickAction.setText("日期：" + (DatePicker1.isEnableClickAction() ? "开启" : "关闭")
                + "；时间：" + (TimePicker1.isEnableClickAction() ? "开启" : "关闭"));
    }

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setText("日期：" + DatePicker1.getText() + "  时间：" + TimePicker1.getText());
    }
}
