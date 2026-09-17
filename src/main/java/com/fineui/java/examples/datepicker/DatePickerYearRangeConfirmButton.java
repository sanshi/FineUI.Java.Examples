package com.fineui.java.examples.datepicker;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** DatePicker 示例（路由 {@code date-picker/date-picker-year-range-confirm-button}）。 */
@FineUIPage("date-picker/date-picker-year-range-confirm-button")
public class DatePickerYearRangeConfirmButton extends PageBase {

    protected DatePicker DatePicker1;
    protected Button btnSubmit;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            DatePicker1.setRangeStartDate(LocalDateTime.now().plusYears(0));
            DatePicker1.setRangeEndDate(LocalDateTime.now().plusYears(5));
            DatePicker1.setMinDate(LocalDateTime.now().minusYears(5));
            DatePicker1.setMaxDate(LocalDateTime.now().plusYears(15));
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setText("年份范围：" + DatePicker1.getText());
    }
}
