package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Display;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 学生扩展模型（ComplexColumnClient 用）：在 Student 基础上加派生列 FamilyObject/HobbyArray（getter 派生，客户端渲染）。 */
public class StudentExtended extends Student {

    /** 爱好数组（getter 派生列）：客户端 renderHobby 逐项转中文名。 */
    @Display(name = "爱好")
    public List<String> getHobbyArray() {
        return getHobby() == null ? List.of() : Arrays.asList(getHobby());
    }

    /** 家庭信息对象（getter 派生列）：客户端 renderFamily 拼父母姓名。 */
    @Display(name = "家庭信息")
    public Map<String, Object> getFamilyObject() {
        Map<String, Object> jo = new LinkedHashMap<>();
        if (getFamily() != null) {
            jo.put("daddy", getFamily().getFatherName());
            jo.put("mommy", getFamily().getMotherName());
        }
        return jo;
    }
}
