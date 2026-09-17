package com.fineui.java.examples.multilang.model;

import com.fineui.java.binding.Compare;
import com.fineui.java.binding.ComparisonOperator;
import com.fineui.java.binding.DataType;
import com.fineui.java.binding.Display;
import com.fineui.java.binding.DisplayFormat;
import com.fineui.java.binding.UICompare;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * 字段大小比较校验的表单数据对象（数据注解版本）：标签与校验消息均走多语言资源
 * （字段比较用自定义注解 {@code @UICompare} 实现）。
 */
public class UICompareModel {

    @Display(nameKey = "Display_Password")
    @DataType(DataType.Type.PASSWORD)
    @NotBlank(message = "#{ErrorMessage_Required}")
    private String password;

    @Display(nameKey = "Display_ConfirmPassword")
    @DataType(DataType.Type.PASSWORD)
    @NotBlank(message = "#{ErrorMessage_Required}")
    @Compare(value = "password", message = "#{ErrorMessage_ConfirmPassword}")
    private String confirmPassword;

    @Display(nameKey = "Display_StartDate")
    @NotNull(message = "#{ErrorMessage_Required}")
    @DisplayFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;

    @Display(nameKey = "Display_EndDate")
    @NotNull(message = "#{ErrorMessage_Required}")
    @DisplayFormat(pattern = "yyyy/MM/dd")
    @UICompare(value = "startDate", operator = ComparisonOperator.GREATER_THAN, message = "#{ErrorMessage_UICompare_GreaterThan}")
    private LocalDate endDate;

    @Display(nameKey = "Display_Number1")
    @NotNull(message = "#{ErrorMessage_Required}")
    private Integer number1;

    @Display(nameKey = "Display_Number2")
    @NotNull(message = "#{ErrorMessage_Required}")
    @UICompare(value = "number1", operator = ComparisonOperator.GREATER_THAN_EQUAL, message = "#{ErrorMessage_UICompare_GreaterThanEqual}")
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
