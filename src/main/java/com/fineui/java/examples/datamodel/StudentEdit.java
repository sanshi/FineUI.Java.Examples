package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.PageContext;
import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.Student;
import com.fineui.java.examples.datamodel.model.StudentStore;

/**
 * 数据模型编辑实体（路由 {@code data-model/student-edit}）。
 *
 * <p>回发时 {@code @BindProperty} 字段是框架新建的<b>空壳</b>，只承载模板里 {@code for} 声明过的那几个表单值，
 * 与数据源没有任何关系。所以保存不能直接拿它整对象写回（没进表单的属性都是默认值，会把已有数据清零），
 * 而要<b>按主键重新读出实体、只覆盖表单里出现过的字段</b>。
 */
@FineUIPage("data-model/student-edit")
public class StudentEdit extends PageBase {

    /** 表单绑定根：首屏填入数据库值；回发时保留用户提交的表单值。 */
    @BindProperty
    private Student student;

    private Student storedStudent;

    public Student getStudent() {
        return student;
    }

    /** 每次按当前 URL 查找编辑目标；首屏才把数据库值填入表单。 */
    public void Page_Init(Object sender, EventArgs e) {
        storedStudent = StudentStore.find(getQueryIntValue("id"));
        if (storedStudent == null) {
            // 记录已删除时登记中止结果；当前方法返回后，框架改渲染极简提示页。
            // 本页常被 Window 以 iframe 弹出，故让用户点「确定」后顺手关掉父级窗体。
            PageContext.abortPage("该学生不存在或已被删除！", ActiveWindow.hideCommand());
            return;
        }
        // 真实项目还应按本次 URL 和当前身份检查编辑权限。
        if (!isPostBack()) {
            student = storedStudent;
        }
    }

    public void btnSave_Click(Object sender, EventArgs e) {
        // 模型上有校验约束、却不在表单里的字段（备注），在空壳上必然报「不能为空」——显式移除。
        getModelState().remove("student.remark");

        if (!getModelState().isValid()) {
            return;
        }

        // 修改 Page_Init 已按本次 URL 查到的实体，只覆盖表单里出现过的字段；
        // 未列出的属性（分组 / 状态 / 爱好 / 家庭信息 / 备注）保持原值。
        // 这份清单必须与模板里的 for 保持一致——往表单加字段而忘了补赋值，该字段就永远保存不上。
        storedStudent.setName(student.getName());
        storedStudent.setGender(student.getGender());
        storedStudent.setEntranceYear(student.getEntranceYear());
        storedStudent.setAtSchool(student.isAtSchool());
        storedStudent.setMajor(student.getMajor());
        storedStudent.setEntranceDate(student.getEntranceDate());

        StudentStore.update(storedStudent);
        showNotify("保存成功！ID=" + storedStudent.getId() + "，姓名=" + storedStudent.getName(), MessageBoxIcon.Success);
    }
}
