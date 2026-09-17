package com.fineui.java.examples.datamodel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.StudentHelper;
import com.fineui.java.examples.datamodel.model.StudentViewModel;

import java.util.List;

/**
 * 数据模型复杂列（路由 {@code data-model/complex-column}）：列走 {@code row-type-from} + 列 {@code for} 相对字段名，
 * 派生列（FamilyInfo/HobbyInfo）由 {@link StudentViewModel} 的 getter 派生（服务端拼中文文案）。
 */
@FineUIPage("data-model/complex-column")
public class ComplexColumn extends PageBase {
    com.fineui.java.core.controls.Grid Grid1;

    // 行模型类型来源（row-type-from 只从此字段泛型 List<StudentViewModel> 反射出行模型，不读数据）
    private List<StudentViewModel> students;

    public List<StudentViewModel> getStudents() {
        return students;
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            students = StudentHelper.getSimpleStudentViewModels();
            Grid1.setDataSource(students);
            Grid1.dataBind();
        }
    }
}
