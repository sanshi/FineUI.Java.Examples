package com.fineui.java.examples.datepicker;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDateTime;

/** DatePicker 示例（路由 {@code date-picker/date-picker-display-year-min-date}）。 */
@FineUIPage("date-picker/date-picker-display-year-min-date")
public class DatePickerDisplayYearMinDate extends PageBase {

    protected DatePicker DatePicker1;
    protected DatePicker DatePicker2;
    protected Button btnSubmit;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            DatePicker1.setSelectedDate(LocalDateTime.now());
            DatePicker1.setMinDate(LocalDateTime.now().minusYears(5));
            DatePicker1.setMaxDate(LocalDateTime.now().plusYears(5));
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setText("开始年份：" + DatePicker1.getText() + "  结束年份：" + DatePicker2.getText());
    }
}
