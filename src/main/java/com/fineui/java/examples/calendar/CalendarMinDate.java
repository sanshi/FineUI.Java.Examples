package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 限定可选范围的日历（路由 {@code calendar/calendar-min-date}）：MinDate=今天、MaxDate=今天+20，范围外日期被禁用。 */
@FineUIPage("calendar/calendar-min-date")
public class CalendarMinDate extends PageBase {

    private static final String DATE_FORMAT = "yyyy/MM/dd";

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setMinDate(LocalDateTime.now());
            Calendar1.setMaxDate(LocalDateTime.now().plusDays(20));

            Calendar1.setDateFormatString(DATE_FORMAT);
            Calendar1.setSelectedDate(LocalDateTime.now().plusDays(10));
            Button1.setText("选中" + format(LocalDateTime.now().plusDays(2)));
        }
    }

    public void Calendar1_DateSelect(Object sender, EventArgs e) {
        updateResult(Calendar1.getSelectedDate());
    }

    public void Button1_Click(Object sender, EventArgs e) {
        LocalDateTime selectedDate = LocalDateTime.now().plusDays(2);
        Calendar1.setSelectedDate(selectedDate);
        updateResult(selectedDate);
    }

    private void updateResult(LocalDateTime date) {
        labResult.setText("选择的日期：" + format(date));
    }

    private static String format(LocalDateTime date) {
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(date);
    }
}
