package com.fineui.java.examples.multilang.model;

import com.fineui.java.binding.DataType;
import com.fineui.java.binding.Display;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 登录页面的表单数据对象（数据注解版本）：字段标签经 {@code @Display(nameKey)} 走多语言资源，
 * 校验消息经 {@code #{资源键}} 走多语言资源。
 */
public class User {

    @Display(nameKey = "Display_UserName")
    @NotBlank(message = "#{ErrorMessage_Required}")
    @Size(max = 20, message = "#{ErrorMessage_StringLength}")
    private String userName;

    @Display(nameKey = "Display_Password")
    @DataType(DataType.Type.PASSWORD)
    @NotBlank(message = "#{ErrorMessage_Required}")
    @Size(min = 3, message = "#{ErrorMessage_MinLength}")
    @Size(max = 9, message = "#{ErrorMessage_MaxLength}")
    @Pattern(regexp = "^(?:[0-9]+[a-zA-Z]|[a-zA-Z]+[0-9])[a-zA-Z0-9]*$", message = "#{ErrorMessage_LetterAndNumber}")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
