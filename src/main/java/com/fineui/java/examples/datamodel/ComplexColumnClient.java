package com.fineui.java.examples.datamodel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.StudentExtended;
import com.fineui.java.examples.datamodel.model.StudentHelper;

import java.util.List;

/**
 * 数据模型复杂列客户端（路由 {@code data-model/complex-column-client}）：与 {@code complex-column} 对照，
 * 列走 {@code row-type-from} + 列 {@code for} 相对字段名，派生列（FamilyObject/HobbyArray）由
 * {@link StudentExtended} 的 getter 派生（返回对象/数组），经客户端渲染函数拼中文文案。
 */
@FineUIPage("data-model/complex-column-client")
public class ComplexColumnClient extends PageBase {
    com.fineui.java.core.controls.Grid Grid1;

    // 行模型类型来源（row-type-from 只从此字段泛型 List<StudentExtended> 反射出行模型，不读数据）
    private List<StudentExtended> students;

    public List<StudentExtended> getStudents() {
        return students;
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            students = StudentHelper.getSimpleStudentExtended();
            Grid1.setDataSource(students);
            Grid1.dataBind();
        }
    }
}
