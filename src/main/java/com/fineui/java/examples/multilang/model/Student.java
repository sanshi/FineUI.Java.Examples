package com.fineui.java.examples.multilang.model;

import com.fineui.java.binding.Display;
import com.fineui.java.binding.DisplayFormat;

import java.time.LocalDate;

/**
 * 表格的学生数据对象（数据注解版本）：列头经 {@code @Display(nameKey)} 走多语言资源，
 * 注册日期经 {@code @DisplayFormat} 推导列渲染格式。
 */
public class Student {

    @Display(nameKey = "Display_Student_Name")
    private String name;

    private int id;

    @Display(nameKey = "Display_Student_Gender")
    private int gender;

    @Display(nameKey = "Display_Student_EntranceYear")
    private int entranceYear;

    @Display(nameKey = "Display_Student_AtSchool")
    private boolean atSchool;

    @Display(nameKey = "Display_Student_Major")
    private String major;

    @Display(nameKey = "Display_Student_Group")
    private int group;

    @Display(nameKey = "Display_Student_EntranceDate")
    @DisplayFormat(pattern = "yyyy/MM/dd")
    private LocalDate entranceDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(int entranceYear) {
        this.entranceYear = entranceYear;
    }

    public boolean isAtSchool() {
        return atSchool;
    }

    public void setAtSchool(boolean atSchool) {
        this.atSchool = atSchool;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public LocalDate getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(LocalDate entranceDate) {
        this.entranceDate = entranceDate;
    }
}
