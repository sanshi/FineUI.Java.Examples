package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.binding.HiddenProperty;
import com.fineui.java.core.AbortPageException;
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

    /** 编辑目标的主键：首屏从 {@code ?id} 取，之后随 __FSTATE 往返，供保存时重新读取数据源用。 */
    @HiddenProperty
    private int studentId;

    /** 表单绑定根：首屏由 Page_Get 加载供回显；回发时框架兜底新建空壳承载表单值。 */
    @BindProperty
    private Student student;

    public Student getStudent() {
        return student;
    }

    /** 首屏渲染前执行（回发不执行，因此无需 if (!isPostBack())）：读 ?id 加载实体，供模板的 for 回显。 */
    public void Page_Get(Object sender, EventArgs e) {
        studentId = getQueryInt("id", 0);
        student = StudentStore.find(studentId);
        if (student == null) {
            // 抛出即中止：不渲染本页模板，改渲染框架内置极简页并弹出提示。
            // 本页常被 Window 以 iframe 弹出，故让用户点「确定」后顺手关掉父级窗体。
            throw new AbortPageException("该学生不存在或已被删除！", ActiveWindow.hideReference());
        }
        // 真实项目在此判断当前用户能不能编辑这条记录，无权则同样抛 AbortPageException。
        // ⚠️ 注意本方法只在首屏执行——回发时不跑，所以鉴权在事件处理器里还要再做一次（见 btnSave_Click）。
        // 本示例数据无归属关系，故只留说明。
    }

    public void btnSave_Click(Object sender, EventArgs e) {
        // 模型上有校验约束、却不在表单里的字段（备注），在空壳上必然报「不能为空」——显式移除。
        getModelState().remove("student.remark");

        if (!getModelState().isValid()) {
            return;
        }

        // 按主键重新读出完整实体，只覆盖表单里出现过的字段；
        // 未列出的属性（分组 / 状态 / 爱好 / 家庭信息 / 备注）保持原值。
        // 这份清单必须与模板里的 for 保持一致——往表单加字段而忘了补赋值，该字段就永远保存不上。
        // studentId 随 __FSTATE 往返，客户端可以篡改成任意主键——回发路径没有 Page_Get，
        // 所以「当前用户能不能编辑这条记录」必须在这里再判一次（真实项目：无权则 showAlert + return）。
        Student stored = StudentStore.find(studentId);
        if (stored == null) {
            showNotify("该学生不存在或已被删除！", MessageBoxIcon.Error);
            return;
        }
        stored.setName(student.getName());
        stored.setGender(student.getGender());
        stored.setEntranceYear(student.getEntranceYear());
        stored.setAtSchool(student.isAtSchool());
        stored.setMajor(student.getMajor());
        stored.setEntranceDate(student.getEntranceDate());

        StudentStore.update(stored);
        showNotify("保存成功！ID=" + stored.getId() + "，姓名=" + stored.getName(), MessageBoxIcon.Success);
    }
}
