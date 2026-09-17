package com.fineui.java.examples.datepicker;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/** DatePicker 联动示例（路由 {@code date-picker/date-picker-date-select}）：OnTextChanged / OnDateSelect 联动设置结束日期。 */
@FineUIPage("date-picker/date-picker-date-select")
public class DatePickerDateSelect extends PageBase {

    protected DatePicker DatePicker1;
    protected DatePicker DatePicker2;
    protected DatePicker DatePicker3;
    protected DatePicker DatePicker4;
    protected Button Button1;
    protected Button Button2;
    protected Label labResult1;
    protected Label labResult2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void DatePicker1_TextChanged(Object sender, EventArgs e) {
        if (DatePicker1.getSelectedDate() != null) {
            DatePicker2.setSelectedDate(DatePicker1.getSelectedDate().plusDays(3));
        }
    }

    public void DatePicker3_DateSelect(Object sender, EventArgs e) {
        if (DatePicker3.getSelectedDate() != null) {
            DatePicker4.setSelectedDate(DatePicker3.getSelectedDate().plusDays(3));
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        labResult1.setText("开始日期：" + DatePicker1.getText() + "  结束日期：" + DatePicker2.getText());
    }

    public void Button2_Click(Object sender, EventArgs e) {
        labResult2.setText("开始日期：" + DatePicker3.getText() + "  结束日期：" + DatePicker4.getText());
    }
}
