package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** 时间选择日历（路由 {@code calendar/calendar-display-time}）：DisplayType=Time，只显示时/分/秒三列，点击时间回发。 */
@FineUIPage("calendar/calendar-display-time")
public class CalendarDisplayTime extends PageBase {

    private static final String DATE_FORMAT = "HH:mm:ss";

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setDateFormatString(DATE_FORMAT);
            Calendar1.setText(LocalTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
            Button1.setText("选中00:00:00");
        }
    }

    public void Calendar1_DateSelect(Object sender, EventArgs e) {
        updateResult(Calendar1.getText());
    }

    public void Button1_Click(Object sender, EventArgs e) {
        String text = "00:00:00";
        Calendar1.setText(text);
        updateResult(text);
    }

    private void updateResult(String text) {
        labResult.setText("选择的时间：" + text);
    }
}
