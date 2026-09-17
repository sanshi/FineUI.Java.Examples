package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Display;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

/** 复选框列表/单选按钮列表的表单数据对象。 */
public class CheckBoxListForm {

    @Display(name = "复选框一")
    private Boolean checked;

    @Display(name = "复选框列表一")
    @NotEmpty(message = "{0}不能为空！")
    private List<String> checkBoxList1 = new ArrayList<>();

    @Display(name = "单选框列表一")
    @NotNull(message = "{0}不能为空！")
    private String radioButtonList1;

    @Display(name = "性别")
    @NotNull(message = "{0}不能为空！")
    private Integer gender;

    @Display(name = "性别（枚举值）")
    @NotNull(message = "{0}不能为空！")
    private GenderType genderType;

    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
    public List<String> getCheckBoxList1() {
        return checkBoxList1;
    }
    public void setCheckBoxList1(List<String> checkBoxList1) {
        this.checkBoxList1 = checkBoxList1;
    }
    public String getRadioButtonList1() {
        return radioButtonList1;
    }
    public void setRadioButtonList1(String radioButtonList1) {
        this.radioButtonList1 = radioButtonList1;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public GenderType getGenderType() {
        return genderType;
    }
    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }
}
