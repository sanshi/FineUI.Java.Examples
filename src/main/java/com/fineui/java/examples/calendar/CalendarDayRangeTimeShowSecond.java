package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 带时间（不含秒）的日期范围日历（路由 {@code calendar/calendar-day-range-time-show-second}）：格式 yyyy/MM/dd HH:mm，时间列不显示秒。 */
@FineUIPage("calendar/calendar-day-range-time-show-second")
public class CalendarDayRangeTimeShowSecond extends PageBase {

    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    private LocalDateTime getStartDate() {
        return LocalDate.now().plusDays(2).atTime(8, 50, 0);
    }

    private LocalDateTime getEndDate() {
        return LocalDate.now().plusDays(20).atTime(11, 50, 0);
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setDateFormatString(DATE_FORMAT);

            Calendar1.setRangeStartDate(LocalDateTime.of(2014, 7, 30, 14, 30, 0));
            Calendar1.setRangeEndDate(LocalDateTime.of(2014, 8, 8, 16, 30, 0));

            Button1.setText("选中范围：" + format(getStartDate()) + " - " + format(getEndDate()));
        }
    }

    public void Calendar1_DateSelect(Object sender, EventArgs e) {
        updateResult();
    }

    public void Button1_Click(Object sender, EventArgs e) {
        Calendar1.setRangeStartDate(getStartDate());
        Calendar1.setRangeEndDate(getEndDate());
        updateResult();
    }

    private void updateResult() {
        labResult.setText("选择的日期：" + Calendar1.getText());
    }

    private static String format(LocalDateTime date) {
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(date);
    }
}
