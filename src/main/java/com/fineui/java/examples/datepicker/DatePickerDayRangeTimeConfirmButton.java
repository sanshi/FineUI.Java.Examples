package com.fineui.java.examples.datepicker;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** DatePicker 示例（路由 {@code date-picker/date-picker-day-range-time-confirm-button}）。 */
@FineUIPage("date-picker/date-picker-day-range-time-confirm-button")
public class DatePickerDayRangeTimeConfirmButton extends PageBase {

    protected DatePicker DatePicker1;
    protected Button btnSubmit;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            DatePicker1.setRangeStartDate(LocalDateTime.of(2014, 7, 30, 14, 30, 0));
            DatePicker1.setRangeEndDate(LocalDateTime.of(2014, 8, 8, 16, 30, 0));
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setText("选择的日期：" + DatePicker1.getText());
    }
}
