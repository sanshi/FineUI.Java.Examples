package com.fineui.java.examples.datamodel;

import com.fineui.java.examples.datamodel.model.ScoreForm;
import com.fineui.java.examples.datamodel.model.StudentForm;

import java.time.LocalDate;

/** 各个 DataModel 表单示例共享的首屏 DTO 工厂。 */
final class DataModelSamples {
    private DataModelSamples() {
    }

    static StudentForm student() {
        StudentForm student = new StudentForm();
        student.setId(101);
        student.setName("张萍萍");
        student.setGender(0);
        student.setAtSchool(true);
        student.setMajor("材料科学与工程系");
        student.setEntranceYear(2000);
        student.setGroup(1);
        student.setEntranceDate(LocalDate.of(2000, 9, 1));
        ScoreForm score = student.getScore();
        score.setChinese(80);
        score.setMath(100);
        score.setPhysics(88);
        score.setChemistry(79);
        return student;
    }
}
