package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;

/** 年份选择日历（路由 {@code calendar/calendar-display-year}）：DisplayType=Year，只显示年份网格，点击年份回发。 */
@FineUIPage("calendar/calendar-display-year")
public class CalendarDisplayYear extends PageBase {

    private static final String DATE_FORMAT = "yyyy";

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setDateFormatString(DATE_FORMAT);
            Calendar1.setText(String.valueOf(LocalDate.now().getYear()));
            Button1.setText("选中" + LocalDate.now().plusYears(2).getYear());
        }
    }

    public void Calendar1_DateSelect(Object sender, EventArgs e) {
        updateResult(Calendar1.getText());
    }

    public void Button1_Click(Object sender, EventArgs e) {
        String text = String.valueOf(LocalDate.now().plusYears(2).getYear());
        Calendar1.setText(text);
        updateResult(text);
    }

    private void updateResult(String text) {
        labResult.setText("选择的年份：" + text);
    }
}
