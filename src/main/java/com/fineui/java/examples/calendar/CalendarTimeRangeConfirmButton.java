package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/** 带确定按钮的时间范围日历（路由 {@code calendar/calendar-time-range-confirm-button}）。 */
@FineUIPage("calendar/calendar-time-range-confirm-button")
public class CalendarTimeRangeConfirmButton extends PageBase {

    private static final String DATE_FORMAT = "HH:mm:ss";

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setDateFormatString(DATE_FORMAT);

            Calendar1.setText("14:30:00 - 16:30:00");

            Button1.setText("选中范围：08:50:00 - 11:50:00");
        }
    }

    public void Calendar1_DateSelect(Object sender, EventArgs e) {
        updateResult();
    }

    public void Button1_Click(Object sender, EventArgs e) {
        Calendar1.setText("08:50:00 - 11:50:00");
        updateResult();
    }

    private void updateResult() {
        labResult.setText("时间范围：" + Calendar1.getText());
    }
}
