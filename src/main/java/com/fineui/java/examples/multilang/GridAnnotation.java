package com.fineui.java.examples.multilang;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.MultilangPageBase;
import com.fineui.java.examples.multilang.model.Student;
import com.fineui.java.examples.multilang.model.StudentHelper;
import org.springframework.context.MessageSource;

import java.util.List;

/**
 * 多语言表格（数据注解，路由 {@code multi-lang/grid-annotation}）：列走 {@code row-type-from="students"} +
 * 列 {@code for} 相对字段名，列头经 {@code @Display(nameKey)} 随语言切换；列类型/日期格式由属性类型与
 * {@code @DisplayFormat} 推导。
 */
@FineUIPage("multi-lang/grid-annotation")
public class GridAnnotation extends MultilangPageBase {

    public GridAnnotation(MessageSource messageSource) {
        super(messageSource);
    }

    // 行模型类型来源（row-type-from 只从此字段泛型 List<Student> 反射出 Student，不读数据）；
    // 实际数据在 Page_Load 里赋值，供 loadData 绑定表格
    private List<Student> students;

    com.fineui.java.core.controls.Grid Grid1;

    public List<Student> getStudents() {
        return students;
    }

    public void Page_Load(Object sender, EventArgs e) {
        // 客户端 JS 资源（key 与 grid.js 内 F.getResource 调用一致）
        setJavaScriptResources("Male", "Female", "GridGender", "GridMajor", "GridRowId", "GridRowText",
                "NoSelectionMessage", "NamePrefix", "IntroPrefix");

        if (!isPostBack()) {
            students = StudentHelper.getSimpleStudentList();
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(students);
        Grid1.dataBind();
    }
}
