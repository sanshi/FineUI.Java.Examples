package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;

/** 独立双面板的范围日历（路由 {@code calendar/calendar-independent-range-panels}）：IndependentRangePanels=true，左右面板各自独立切换年月。 */
@FineUIPage("calendar/calendar-independent-range-panels")
public class CalendarIndependentRangePanels extends PageBase {

    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected com.fineui.java.core.controls.Calendar Calendar2;
    protected com.fineui.java.core.controls.Calendar Calendar3;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setRangeStartDate(LocalDate.parse("2020-03-02").atStartOfDay());
            Calendar1.setRangeEndDate(LocalDate.parse("2026-08-18").atStartOfDay());

            Calendar2.setRangeStartDate(LocalDate.parse("2020-03-01").atStartOfDay());
            Calendar2.setRangeEndDate(LocalDate.parse("2026-08-01").atStartOfDay());

            Calendar3.setRangeStartDate(LocalDate.parse("2001-01-01").atStartOfDay());
            Calendar3.setRangeEndDate(LocalDate.parse("2026-01-01").atStartOfDay());
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        labResult.setText(String.format("日期范围：%s<br>月份范围：%s<br>年份范围：%s",
                Calendar1.getText(), Calendar2.getText(), Calendar3.getText()));
    }
}
