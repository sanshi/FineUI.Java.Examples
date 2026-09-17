package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Display;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

/** 下拉列表的表单数据对象。 */
public class DropDownListForm {

    @Display(name = "下拉列表一")
    @NotBlank(message = "{0}不能为空！")
    private String dropDownList1;

    @Display(name = "下拉列表二")
    @NotEmpty(message = "{0}不能为空！")
    private List<String> dropDownList2 = new ArrayList<>();

    public String getDropDownList1() {
        return dropDownList1;
    }
    public void setDropDownList1(String dropDownList1) {
        this.dropDownList1 = dropDownList1;
    }
    public List<String> getDropDownList2() {
        return dropDownList2;
    }
    public void setDropDownList2(List<String> dropDownList2) {
        this.dropDownList2 = dropDownList2;
    }
}
