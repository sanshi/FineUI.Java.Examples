package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.TimePicker;
import com.fineui.java.examples.code.PageBase;

@FineUIPage("form/appointment-consultation")
public class AppointmentConsultation extends PageBase {
    protected DropDownList ddlService;
    protected TimePicker tpAppointment;
    protected Label labResult;

    public void ddlService_SelectedIndexChanged(Object sender, EventArgs e) {
        int minutes = "extended".equals(ddlService.getSelectedValue()) ? 30 : 15;
        tpAppointment.setIncrement(minutes);
        labResult.setHidden(true);
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        String service = ddlService.getSelectedValue();
        String time = tpAppointment.getText();
        labResult.setHidden(false);

        // 两种咨询都能约整点和半点，09:15、09:45 只开放给常规咨询。
        boolean sharedSlot = "09:00".equals(time) || "09:30".equals(time) || "10:00".equals(time);
        boolean standardOnlySlot = "09:15".equals(time) || "09:45".equals(time);
        boolean available = ("standard".equals(service) && (sharedSlot || standardOnlySlot))
                || ("extended".equals(service) && sharedSlot);
        if (!available) {
            labResult.setText("请选择当前咨询项目可预约的开始时间。");
            return;
        }

        labResult.setText("预约已提交：" + ddlService.getText() + "，开始时间：" + time);
    }
}
