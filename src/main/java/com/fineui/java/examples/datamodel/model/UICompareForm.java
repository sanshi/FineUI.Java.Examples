package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Compare;
import com.fineui.java.binding.ComparisonOperator;
import com.fineui.java.binding.DataType;
import com.fineui.java.binding.Display;
import com.fineui.java.binding.DisplayFormat;
import com.fineui.java.binding.UICompare;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/** 字段大小比较校验的表单数据对象。 */
public class UICompareForm {
    @Display(name = "密码")
    @DataType(DataType.Type.PASSWORD)
    @NotBlank(message = "{0}不能为空！")
    private String password;
    @Display(name = "确认密码")
    @DataType(DataType.Type.PASSWORD)
    @NotBlank(message = "{0}不能为空！")
    @Compare(value = "password", message = "两次输入的密码要相同！")
    private String confirmPassword;
    @Display(name = "开始日期")
    @NotNull(message = "{0}不能为空！")
    @DisplayFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;
    @Display(name = "结束日期")
    @NotNull(message = "{0}不能为空！")
    @DisplayFormat(pattern = "yyyy/MM/dd")
    @UICompare(value = "startDate", operator = ComparisonOperator.GREATER_THAN, message = "{0}应该大于{1}！")
    private LocalDate endDate;
    @Display(name = "数字 1")
    @NotNull(message = "{0}不能为空！")
    private Integer number1;
    @Display(name = "数字 2")
    @NotNull(message = "{0}不能为空！")
    @UICompare(value = "number1", operator = ComparisonOperator.GREATER_THAN_EQUAL, message = "{0}应该大于等于{1}的值！")
    private Integer number2;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public Integer getNumber1() {
        return number1;
    }
    public void setNumber1(Integer number1) {
        this.number1 = number1;
    }
    public Integer getNumber2() {
        return number2;
    }
    public void setNumber2(Integer number2) {
        this.number2 = number2;
    }
}
