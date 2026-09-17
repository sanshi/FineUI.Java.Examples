package com.fineui.java.examples.datamodel.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 学生编辑的内存数据源：用一个静态列表模拟「数据库 + 按主键读取/更新」。
 *
 * <p><b>{@link #find(int)} 刻意返回副本而不是列表里的对象本身</b>，为的是让这个内存数据源的行为和真实
 * 数据库/ORM 一致——后者每次查询都给你一个新对象，而静态列表是唯一会把同一个对象反复递出去的数据源。
 * 若返回活引用，事件处理器里的 {@code stored.setName(...)} 就直接改了列表里的记录，随后的
 * {@link #update(Student)} 退化成「把同一个引用再放回原位」的空操作——示例演示的「读取 → 覆盖 → 写回」
 * 三步里，第三步就成了摆设。
 */
public final class StudentStore {

    // 用 CopyOnWriteArrayList：这份静态列表被所有请求共享，find 的遍历与 update 的写入会并发。
    private static final List<Student> STUDENTS = new CopyOnWriteArrayList<>(StudentHelper.getSimpleStudentList());

    private StudentStore() {
    }

    /** 按主键读取一份副本（不存在返回 {@code null}）。 */
    public static Student find(int id) {
        Student found = STUDENTS.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        return found == null ? null : copyOf(found);
    }

    /** 按主键写回（不存在则追加，模拟「新增」分支）。 */
    public static void update(Student edited) {
        for (int i = 0; i < STUDENTS.size(); i++) {
            if (STUDENTS.get(i).getId() == edited.getId()) {
                STUDENTS.set(i, copyOf(edited));
                return;
            }
        }
        STUDENTS.add(copyOf(edited));
    }

    /** 逐属性复制（模拟从数据库读出一个新对象）。 */
    private static Student copyOf(Student source) {
        Student copy = new Student();
        copy.setId(source.getId());
        copy.setName(source.getName());
        copy.setGender(source.getGender());
        copy.setEntranceYear(source.getEntranceYear());
        copy.setAtSchool(source.isAtSchool());
        copy.setMajor(source.getMajor());
        copy.setGroup(source.getGroup());
        copy.setEntranceDate(source.getEntranceDate());
        copy.setCurrentStatus(source.getCurrentStatus());
        copy.setHobby(source.getHobby() == null ? null : source.getHobby().clone());
        copy.setFamily(copyOf(source.getFamily()));
        copy.setRemark(source.getRemark());
        return copy;
    }

    /** 嵌套对象同样复制——否则「返回副本」的承诺对 family 不成立，改副本会写穿数据源。 */
    private static Family copyOf(Family source) {
        if (source == null) {
            return null;
        }
        Family copy = new Family();
        copy.setFatherName(source.getFatherName());
        copy.setMotherName(source.getMotherName());
        return copy;
    }
}
