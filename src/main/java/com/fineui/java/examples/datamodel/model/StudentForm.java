package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Display;
import com.fineui.java.binding.DisplayFormat;
import com.fineui.java.binding.Key;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/** 学生数据对象（含二级属性与隐藏属性字段）。 */
public class StudentForm {
    @Key private Integer id;
    @Display(name = "姓名")
    @NotBlank(message = "{0}不能为空！")
    @Size(max = 20)
    private String name;
    @Display(name = "性别")
    @NotNull(message = "{0}不能为空！")
    private Integer gender;
    @Display(name = "是否在校")
    @NotNull(message = "{0}不能为空！")
    private Boolean atSchool;
    @Display(name = "所学专业")
    @NotBlank(message = "{0}不能为空！")
    @Size(max = 200)
    private String major;
    @Display(name = "注册日期")
    @DisplayFormat(pattern = "yyyy/MM/dd")
    private LocalDate entranceDate;
    private Integer entranceYear;
    private Integer group;
    @Display(name = "考试成绩")
    private ScoreForm score = new ScoreForm();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public Boolean getAtSchool() {
        return atSchool;
    }
    public void setAtSchool(Boolean atSchool) {
        this.atSchool = atSchool;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public LocalDate getEntranceDate() {
        return entranceDate;
    }
    public void setEntranceDate(LocalDate entranceDate) {
        this.entranceDate = entranceDate;
    }
    public Integer getEntranceYear() {
        return entranceYear;
    }
    public void setEntranceYear(Integer entranceYear) {
        this.entranceYear = entranceYear;
    }
    public Integer getGroup() {
        return group;
    }
    public void setGroup(Integer group) {
        this.group = group;
    }
    public ScoreForm getScore() {
        return score;
    }
    public void setScore(ScoreForm score) {
        this.score = score;
    }
}
