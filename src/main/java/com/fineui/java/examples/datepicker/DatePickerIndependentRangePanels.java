package com.fineui.java.examples.datepicker;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;

/** DatePicker 独立双面板范围示例（路由 {@code date-picker/date-picker-independent-range-panels}）：日/月/年范围，左右面板独立切换。 */
@FineUIPage("date-picker/date-picker-independent-range-panels")
public class DatePickerIndependentRangePanels extends PageBase {

    protected DatePicker DatePicker1;
    protected DatePicker DatePicker2;
    protected DatePicker DatePicker3;
    protected Button btnSubmit;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            DatePicker1.setRangeStartDate(LocalDate.parse("2020-03-02").atStartOfDay());
            DatePicker1.setRangeEndDate(LocalDate.parse("2026-08-18").atStartOfDay());

            DatePicker2.setRangeStartDate(LocalDate.parse("2020-03-01").atStartOfDay());
            DatePicker2.setRangeEndDate(LocalDate.parse("2026-08-01").atStartOfDay());

            DatePicker3.setRangeStartDate(LocalDate.parse("2001-01-01").atStartOfDay());
            DatePicker3.setRangeEndDate(LocalDate.parse("2026-01-01").atStartOfDay());
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setText(String.format("日期范围：%s<br>月份范围：%s<br>年份范围：%s",
                DatePicker1.getText(), DatePicker2.getText(), DatePicker3.getText()));
    }
}
