package com.fineui.java.examples.datepicker;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** DatePicker 示例（路由 {@code date-picker/date-picker-day-range-time-no-initial-value}）。 */
@FineUIPage("date-picker/date-picker-day-range-time-no-initial-value")
public class DatePickerDayRangeTimeNoInitialValue extends PageBase {

    protected DatePicker DatePicker1;
    protected Button btnSubmit;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 不设初始范围
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setText("选择的日期：" + DatePicker1.getText());
    }
}
