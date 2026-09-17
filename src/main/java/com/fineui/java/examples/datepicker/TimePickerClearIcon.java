package com.fineui.java.examples.datepicker;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDateTime;

/** 带清除图标的日期/时间选择器示例（路由 {@code date-picker/time-picker-clear-icon}）：AutoShowClearIcon=true。 */
@FineUIPage("date-picker/time-picker-clear-icon")
public class TimePickerClearIcon extends PageBase {

    protected DatePicker DatePicker1;
    protected com.fineui.java.core.controls.TimePicker TimePicker1;
    protected Button btnSubmit;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            DatePicker1.setSelectedDate(LocalDateTime.now());
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setText("日期：" + DatePicker1.getText() + "  时间：" + TimePicker1.getText());
    }
}
