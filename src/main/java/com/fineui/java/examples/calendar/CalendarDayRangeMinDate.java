package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 限定范围的日期范围选择日历（路由 {@code calendar/calendar-day-range-min-date}）：MinDate=今天、MaxDate=今天+30。 */
@FineUIPage("calendar/calendar-day-range-min-date")
public class CalendarDayRangeMinDate extends PageBase {

    private static final String DATE_FORMAT = "yyyy/MM/dd";

    private final LocalDateTime startDate = LocalDateTime.now().plusDays(2);
    private final LocalDateTime endDate = LocalDateTime.now().plusDays(20);

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setDateFormatString(DATE_FORMAT);

            Calendar1.setMinDate(LocalDateTime.now());
            Calendar1.setMaxDate(LocalDateTime.now().plusDays(30));

            Calendar1.setRangeStartDate(LocalDateTime.now().plusDays(5));
            Calendar1.setRangeEndDate(LocalDateTime.now().plusDays(10));

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
        labResult.setText("选择的日期：" + Calendar1.getText());
    }

    private static String format(LocalDateTime date) {
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(date);
    }
}
