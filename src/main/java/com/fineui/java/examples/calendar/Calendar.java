package com.fineui.java.examples.calendar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 日历基础演示页（路由 {@code calendar/calendar}）：点击某天触发服务端 OnDateSelect 回发、更新结果标签。 */
@FineUIPage("calendar/calendar")
public class Calendar extends PageBase {

    private static final String DATE_FORMAT = "yyyy/MM/dd";

    // 字段类型用全限定名，避免与本示例页类名 Calendar 冲突。
    protected com.fineui.java.core.controls.Calendar Calendar1;
    protected Button Button1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Calendar1.setDateFormatString(DATE_FORMAT);
            Calendar1.setSelectedDate(LocalDateTime.now().plusDays(10));
            Button1.setText("选中" + format(LocalDateTime.now().plusDays(2)));
        }
    }

    public void Calendar1_DateSelect(Object sender, EventArgs e) {
        updateResult(Calendar1.getSelectedDate());
    }

    public void Button1_Click(Object sender, EventArgs e) {
        LocalDateTime selected = LocalDateTime.now().plusDays(2);
        Calendar1.setSelectedDate(selected);
        updateResult(selected);
    }

    private void updateResult(LocalDateTime date) {
        labResult.setText("选择的日期：" + format(date));
    }

    private static String format(LocalDateTime date) {
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(date);
    }
}
