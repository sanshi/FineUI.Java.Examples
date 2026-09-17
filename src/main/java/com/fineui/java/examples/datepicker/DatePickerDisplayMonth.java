package com.fineui.java.examples.datepicker;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDateTime;

/** DatePicker 示例（路由 {@code date-picker/date-picker-display-month}）。 */
@FineUIPage("date-picker/date-picker-display-month")
public class DatePickerDisplayMonth extends PageBase {

    protected DatePicker DatePicker1;
    protected DatePicker DatePicker2;
    protected Button btnSubmit;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            DatePicker1.setSelectedDate(LocalDateTime.now());
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setText("开始年月：" + DatePicker1.getText() + "  结束年月：" + DatePicker2.getText());
    }
}
