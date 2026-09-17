package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** 月份选择日历（路由 {@code calendar/calendar-display-month}）：DisplayType=Month，只显示月份网格，点击月份回发。 */
@FineUIPage("calendar/calendar-display-month")
public class CalendarDisplayMonth extends PageBase {

    private static final String DATE_FORMAT = "yyyy/MM";

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setDateFormatString(DATE_FORMAT);
            Calendar1.setText(LocalDate.now().format(fmt()));
            Button1.setText("选中" + LocalDate.now().plusMonths(2).format(fmt()));
        }
    }

    public void Calendar1_DateSelect(Object sender, EventArgs e) {
        updateResult(Calendar1.getText());
    }

    public void Button1_Click(Object sender, EventArgs e) {
        String text = LocalDate.now().plusMonths(2).format(fmt());
        Calendar1.setText(text);
        updateResult(text);
    }

    private void updateResult(String text) {
        labResult.setText("选择的年月：" + text);
    }

    private static DateTimeFormatter fmt() {
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }
}
