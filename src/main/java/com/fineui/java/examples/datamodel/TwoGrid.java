package com.fineui.java.examples.datamodel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.Student;
import com.fineui.java.examples.datamodel.model.StudentHelper;

import java.util.List;

/**
 * 数据模型多表（路由 {@code data-model/two-grid}）：同一学生模型的两个表格并排——「在校生」与「毕业生」，
 * 通过分部片段 {@code two-grid-partial}（th:replace）复用同一份列结构，行模型经各自的集合字段泛型反射推导。
 */
@FineUIPage("data-model/two-grid")
public class TwoGrid extends PageBase {
    com.fineui.java.core.controls.Grid Grid1;
    com.fineui.java.core.controls.Grid Grid2;

    // 行模型类型来源（row-type-from 只从此字段泛型 List<Student> 反射出 Student，不读数据）
    private List<Student> students1;
    private List<Student> students2;

    public List<Student> getStudents1() {
        return students1;
    }

    public List<Student> getStudents2() {
        return students2;
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            List<Student> all = StudentHelper.getSimpleStudentList();
            students1 = all.stream().filter(Student::isAtSchool).toList();
            students2 = all.stream().filter(s -> !s.isAtSchool()).toList();
            Grid1.setDataSource(students1);
            Grid1.dataBind();
            Grid2.setDataSource(students2);
            Grid2.dataBind();
        }
    }
}
