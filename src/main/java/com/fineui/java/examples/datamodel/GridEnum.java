package com.fineui.java.examples.datamodel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.Student;
import com.fineui.java.examples.datamodel.model.StudentHelper;

import java.util.List;

/**
 * 数据模型枚举列（路由 {@code data-model/grid-enum}）：列走 {@code row-type-from} + 列 {@code for} 相对字段名，
 * 状态列经 {@link Student} 的 Status 枚举（数值渲染）+ currentStatusDisplay（显示名 getter 派生）演示枚举的两种形态。
 */
@FineUIPage("data-model/grid-enum")
public class GridEnum extends PageBase {
    com.fineui.java.core.controls.Grid Grid1;

    // 行模型类型来源（row-type-from 只从此字段泛型 List<Student> 反射出 Student，不读数据）；数据在 Page_Load 赋值
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
