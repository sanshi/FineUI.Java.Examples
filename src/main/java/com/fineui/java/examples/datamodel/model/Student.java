package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Display;
import com.fineui.java.binding.DisplayFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * 表格的学生数据对象：列头/类型/日期格式由 {@code @Display} / {@code @DisplayFormat}
 * 注解推导，状态列经 {@link Status} 枚举（数值渲染）+ {@link #getCurrentStatusDisplay()}（显示名 getter 派生）
 * 演示枚举列的两种形态。
 */
public class Student {

    private int id;

    @Display(name = "姓名")
    private String name;

    @Display(name = "性别")
    private int gender;

    @Display(name = "入学年份")
    private int entranceYear;

    @Display(name = "是否在校")
    private boolean atSchool;

    @Display(name = "所学专业")
    private String major;

    @Display(name = "分组")
    private int group;

    @Display(name = "注册日期")
    @DisplayFormat(pattern = "yyyy/MM/dd")
    private LocalDate entranceDate;

    @Display(name = "状态")
    private Status currentStatus;

    @Display(name = "爱好")
    private String[] hobby;

    @Display(name = "家庭信息")
    private Family family;

    // 有校验约束、但两个编辑示例的表单里都没有它。用来演示：模型完整加载时校验自然通过；
    // 若回发时模型是空壳，它必然报「不能为空」，只能靠 ModelState.remove 手工排除。
    @Display(name = "备注")
    @NotBlank(message = "{0}不能为空！")
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }

    public Family getFamily() {
        return family;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    /** 状态显示名（getter 派生列）：反射 {@link Status} 枚举的 {@code @Display} 取中文名。 */
    @Display(name = "状态（Display）")
    public String getCurrentStatusDisplay() {
        return currentStatus == null ? "" : currentStatus.displayName();
    }
}
