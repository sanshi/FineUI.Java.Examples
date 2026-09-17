package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.DataType;
import com.fineui.java.binding.Display;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/** 登录页面的表单数据对象。 */
public class LoginForm {

    @Display(name = "用户名")
    @NotBlank(message = "{0}不能为空！")
    @Size(max = 20, message = "用户名最多为 20 个字符！")
    private String userName;

    @Display(name = "密码")
    @NotBlank(message = "{0}不能为空！")
    @Size(min = 3, message = "{0}最小为 3 个字符！")
    @Size(max = 9, message = "{0}最大为 9 个字符！")
    @DataType(DataType.Type.PASSWORD)
    @Pattern(regexp = "^(?:[0-9]+[a-zA-Z]|[a-zA-Z]+[0-9])[a-zA-Z0-9]*$", message = "{0}至少包含一个字母和数字！")
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
