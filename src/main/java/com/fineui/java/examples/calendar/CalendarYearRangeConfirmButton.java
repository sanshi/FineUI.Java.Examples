package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** 带确定按钮的年份范围日历（路由 {@code calendar/calendar-year-range-confirm-button}）。 */
@FineUIPage("calendar/calendar-year-range-confirm-button")
public class CalendarYearRangeConfirmButton extends PageBase {

    private static final String DATE_FORMAT = "yyyy";

    private final int startYear = LocalDate.now().plusYears(2).getYear();
    private final int endYear = LocalDate.now().plusYears(10).getYear();

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setDateFormatString(DATE_FORMAT);

            Calendar1.setRangeStartDate(LocalDateTime.now().plusYears(0));
            Calendar1.setRangeEndDate(LocalDateTime.now().plusYears(5));

            Calendar1.setMinDate(LocalDateTime.now().minusYears(5));
            Calendar1.setMaxDate(LocalDateTime.now().plusYears(15));

            Button1.setText("选中范围：" + startYear + " - " + endYear);
        }
    }

    public void Calendar1_DateSelect(Object sender, EventArgs e) {
        updateResult();
    }

    public void Button1_Click(Object sender, EventArgs e) {
        Calendar1.setText(startYear + " - " + endYear);
        updateResult();
    }

    private void updateResult() {
        labResult.setText("年份范围：" + Calendar1.getText());
    }
}
