package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 月份范围选择日历（路由 {@code calendar/calendar-month-range}）：DisplayType=MonthRange，选起止月份。 */
@FineUIPage("calendar/calendar-month-range")
public class CalendarMonthRange extends PageBase {

    private static final String DATE_FORMAT = "yyyy-MM";

    private final LocalDateTime startDate = LocalDateTime.now().plusMonths(2);
    private final LocalDateTime endDate = LocalDateTime.now().plusMonths(10);

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setDateFormatString(DATE_FORMAT);

            Calendar1.setRangeStartDate(LocalDateTime.now().plusMonths(0));
            Calendar1.setRangeEndDate(LocalDateTime.now().plusMonths(5));

            Calendar1.setMinDate(LocalDateTime.now().minusMonths(5));
            Calendar1.setMaxDate(LocalDateTime.now().plusMonths(15));

            Button1.setText("选中范围：" + format(startDate) + " - " + format(endDate));
        }
    }

    public void Calendar1_DateSelect(Object sender, EventArgs e) {
        updateResult();
    }

    public void Button1_Click(Object sender, EventArgs e) {
        Calendar1.setRangeStartDate(startDate);
        Calendar1.setRangeEndDate(endDate);
        updateResult();
    }

    private void updateResult() {
        labResult.setText("月份范围：" + Calendar1.getText());
    }

    private static String format(LocalDateTime date) {
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(date);
    }
}
