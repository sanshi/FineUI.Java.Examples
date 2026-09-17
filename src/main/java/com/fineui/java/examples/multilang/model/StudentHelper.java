package com.fineui.java.examples.multilang.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** 多语言表格的学生数据。 */
public final class StudentHelper {

    private StudentHelper() {
    }

    /** 返回用于「多语言表格（数据注解）」示例的简单学生列表（12 行，字段与 {@link Student} 对齐）。 */
    public static List<Student> getSimpleStudentList() {
        List<Student> students = new ArrayList<>();
        students.add(newStudent(101, "张萍萍", 0, 2000, true, "材料科学与工程系", 1, "2000-09-01"));
        students.add(newStudent(102, "陈飞", 1, 2000, false, "化学系", 1, "2001-09-01"));
        students.add(newStudent(103, "董婷婷", 0, 2000, true, "化学系", 1, "2008-09-01"));
        students.add(newStudent(104, "刘国", 1, 2002, false, "化学系", 2, "2002-09-01"));
        students.add(newStudent(105, "康颖颖", 0, 2008, true, "数学系", 2, "2008-09-01"));
        students.add(newStudent(106, "彭博", 1, 2008, true, "数学系", 3, "2003-09-01"));
        students.add(newStudent(107, "黄婷婷", 0, 2008, true, "数学系", 3, "2000-09-01"));
        students.add(newStudent(108, "唐超", 1, 2004, false, "物理系", 4, "2004-09-01"));
        students.add(newStudent(109, "杨婷婷", 0, 2004, true, "物理系", 4, "2003-09-01"));
        students.add(newStudent(110, "徐鹏", 1, 2002, false, "物理系", 4, "2002-09-01"));
        students.add(newStudent(111, "董国", 1, 2012, true, "自动化系", 5, "2006-09-01"));
        students.add(newStudent(112, "张三石", 1, 2012, true, "材料科学与工程系", 5, "2000-09-01"));
        return students;
    }

    private static Student newStudent(int id, String name, int gender, int entranceYear, boolean atSchool,
                                      String major, int group, String entranceDate) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setGender(gender);
        student.setEntranceYear(entranceYear);
        student.setAtSchool(atSchool);
        student.setMajor(major);
        student.setGroup(group);
        student.setEntranceDate(LocalDate.parse(entranceDate));
        return student;
    }
}
