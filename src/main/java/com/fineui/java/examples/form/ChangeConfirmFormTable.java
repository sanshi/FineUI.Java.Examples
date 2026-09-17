package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 关闭页面前的确认框（表单或表格改变，路由 {@code form/change-confirm-form-table}）：
 * 表单 + 表格（单击单元格编辑）一起做「脏数据」跟踪——表单字段变脏或表格有修改
 * （{@code getModifiedData()}）都会在离开页面时提示；保存后清除两者的脏状态。
 */
@FineUIPage("form/change-confirm-form-table")
public class ChangeConfirmFormTable extends PageBase {

    SimpleForm SimpleForm1;
    NumberBox NumberBox1;
    TextBox TextBox1;
    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void btnClosePostBack_Click(Object sender, EventArgs e) {
        // 保存数据后，清空面板内表单字段的改变状态
        SimpleForm1.clearDirty();
        // 读回表格的修改数据（本页只演示读回，不真正落库）
        Grid1.getModifiedData();
        showNotify("表单和表格提交成功！");
    }
}
