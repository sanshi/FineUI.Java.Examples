package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 带时间但不显示秒的日历（路由 {@code calendar/calendar-time-show-second}）：ShowTime=true、ShowSecond=false，时间列只有时/分。 */
@FineUIPage("calendar/calendar-time-show-second")
public class CalendarTimeShowSecond extends PageBase {

    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setDateFormatString(DATE_FORMAT);
            Calendar1.setSelectedDate(LocalDateTime.now().plusDays(10));

            LocalDateTime newDate = LocalDate.now().plusDays(2).atStartOfDay();
            Button1.setText("选中" + format(newDate));
        }
    }

    public void Calendar1_DateSelect(Object sender, EventArgs e) {
        updateResult(Calendar1.getSelectedDate());
    }

    public void Button1_Click(Object sender, EventArgs e) {
        LocalDateTime newDate = LocalDate.now().plusDays(2).atStartOfDay();
        Calendar1.setSelectedDate(newDate);
        updateResult(newDate);
    }

    private void updateResult(LocalDateTime date) {
        labResult.setText("选择的日期：" + format(date));
    }

    private static String format(LocalDateTime date) {
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(date);
    }
}
