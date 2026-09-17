package com.fineui.java.examples.datamodel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.Student;
import com.fineui.java.examples.datamodel.model.StudentHelper;

import java.util.List;

/**
 * 数据模型二级属性（路由 {@code data-model/complex-property}）：列走 {@code row-type-from} + 列 {@code for}
 * 相对字段名，Family 嵌套对象经 {@code for="family.fatherName"} 等二级路径推导。
 */
@FineUIPage("data-model/complex-property")
public class ComplexProperty extends PageBase {
    com.fineui.java.core.controls.Grid Grid1;

    // 行模型类型来源（row-type-from 只从此字段泛型 List<Student> 反射出 Student，不读数据）
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            students = StudentHelper.getSimpleStudentList();
            Grid1.setDataSource(students);
            Grid1.dataBind();
        }
    }
}
