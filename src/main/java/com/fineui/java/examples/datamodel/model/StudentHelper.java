package com.fineui.java.examples.datamodel.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/** 表格的学生数据。 */
public final class StudentHelper {

    private StudentHelper() {
    }

    // 基础字段（12 行）：id、姓名、性别、入学年份、是否在校、专业、分组、注册日期
    private static final Object[][] BASE = {
            {101, "张萍萍", 0, 2000, true, "材料科学与工程系", 1, "2000-09-01"},
            {102, "陈飞", 1, 2000, false, "化学系", 1, "2001-09-01"},
            {103, "董婷婷", 0, 2000, true, "化学系", 1, "2008-09-01"},
            {104, "刘国", 1, 2002, false, "化学系", 2, "2002-09-01"},
            {105, "康颖颖", 0, 2008, true, "数学系", 2, "2008-09-01"},
            {106, "彭博", 1, 2008, true, "数学系", 3, "2003-09-01"},
            {107, "黄婷婷", 0, 2008, true, "数学系", 3, "2000-09-01"},
            {108, "唐超", 1, 2004, false, "物理系", 4, "2004-09-01"},
            {109, "杨婷婷", 0, 2004, true, "物理系", 4, "2003-09-01"},
            {110, "徐鹏", 1, 2002, false, "物理系", 4, "2002-09-01"},
            {111, "董国", 1, 2012, true, "自动化系", 5, "2006-09-01"},
            {112, "张三石", 1, 2012, true, "材料科学与工程系", 5, "2000-09-01"},
    };

    // 扩展字段（12 行，与 BASE 按索引对应）：父亲、母亲、爱好数组、状态
    private static final Object[][] EXTRAS = {
            {"张国栋", "李梅", new String[]{"reading", "basketball", "travel"}, Status.Excellent},
            {"陈国梁", "周兰", new String[]{"reading", "basketball"}, Status.Good},
            {"董辅仁", "刘静", new String[]{"reading", "basketball", "music"}, Status.Excellent},
            {"刘房龄", "湘采荷", new String[]{"reading", "music"}, Status.MakeUp},
            {"康有为", "陆小妹", new String[]{"travel", "movie", "music"}, Status.Retake},
            {"彭起", "张慧芝", new String[]{"basketball", "movie", "music"}, Status.Retake},
            {"黄世仁", "蔡青澄", new String[]{"reading", "travel", "movie", "music"}, Status.Good},
            {"唐三友", "郑可馨", new String[]{"reading", "movie", "music"}, Status.MakeUp},
            {"杨嘉蒋", "唐怡香", new String[]{"reading", "travel", "music"}, Status.Excellent},
            {"徐侠客", "冯美琳", new String[]{"reading", "basketball", "movie", "travel", "music"}, Status.Retake},
            {"董致远", "蔡诗茵", new String[]{"reading", "basketball"}, Status.Excellent},
            {"张御风", "王语嫣", new String[]{"reading", "basketball", "music"}, Status.Good},
    };

    /** 返回 12 行学生数据（行模型为 {@link Student}）。 */
    public static List<Student> getSimpleStudentList() {
        return getSimpleStudentList(Student::new);
    }

    /** 返回 12 行学生数据（行模型为 {@link StudentViewModel}，含 FamilyInfo/HobbyInfo 派生列）。 */
    public static List<StudentViewModel> getSimpleStudentViewModels() {
        return getSimpleStudentList(StudentViewModel::new);
    }

    /** 返回 12 行学生数据（行模型为 {@link StudentExtended}，含 FamilyObject/HobbyArray 派生列）。 */
    public static List<StudentExtended> getSimpleStudentExtended() {
        return getSimpleStudentList(StudentExtended::new);
    }

    private static <T extends Student> List<T> getSimpleStudentList(Supplier<T> factory) {
        List<T> students = new ArrayList<>();
        for (int i = 0; i < BASE.length; i++) {
            students.add(newStudent(factory.get(), i));
        }
        return students;
    }

    private static <T extends Student> T newStudent(T student, int index) {
        Object[] base = BASE[index];
        Object[] extra = EXTRAS[index];
        student.setId((int) base[0]);
        student.setName((String) base[1]);
        student.setGender((int) base[2]);
        student.setEntranceYear((int) base[3]);
        student.setAtSchool((boolean) base[4]);
        student.setMajor((String) base[5]);
        student.setGroup((int) base[6]);
        student.setEntranceDate(LocalDate.parse((String) base[7]));
        student.setRemark("备注-" + base[0]);   // 有约束、不进表单的字段，供编辑示例演示校验差异

        Family family = new Family();
        family.setFatherName((String) extra[0]);
        family.setMotherName((String) extra[1]);
        student.setFamily(family);
        student.setHobby((String[]) extra[2]);
        student.setCurrentStatus((Status) extra[3]);
        return student;
    }
}
